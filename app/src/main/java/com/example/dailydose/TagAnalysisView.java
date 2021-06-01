package com.example.dailydose;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.MarkerType;
import com.anychart.enums.Position;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;


public class TagAnalysisView extends AppCompatActivity{

    /*
        local variables carrying the data through
     */

    private static boolean startOrEnd = true;
    private static Date startDate;
    private static Date endDate;
    private static String tag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the view to be the avg_rating_analysis xml file
        setContentView(R.layout.activity_tag_analysis);

        // Set the support action bar to be the one defined in the xml file,
        // includes menu
        setSupportActionBar(findViewById(R.id.header_tag));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        startDate = new Date();
        endDate = new Date();

        // initializing the buttons and their listeners to pop up calendars
        Button startDateBtn = (Button) findViewById(R.id.button_start_date);
        Button endDateBtn = (Button) findViewById(R.id.button_end_date);

        startDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOrEnd = true;
                showDatePickerDialog(v);
            }
        });

        endDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOrEnd = false;
                showDatePickerDialog(v);
            }
        });

        // read all used tags in our entries and generate a dropdown list (Spinner widget)
        String[] tags_arr = new String[JsonUtils.getAllUsedTags("TestFile.json", TagAnalysisView.this).size()];
        tags_arr = JsonUtils.getAllUsedTags("TestFile.json", TagAnalysisView.this).toArray(tags_arr);

        Spinner dropdown = (Spinner) findViewById(R.id.tag_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(TagAnalysisView.this, android.R.layout.simple_list_item_1, tags_arr);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                   tag = parent.getItemAtPosition(position).toString();
                                               }

                                               @Override
                                               public void onNothingSelected(AdapterView<?> parent) {
                                                    tag = parent.getItemAtPosition(parent.getFirstVisiblePosition()).toString();
                                                    if(tag == null){
                                                        tag = "";
                                                    }
                                               }
                                           }
        );

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        //Get the anyChartView and Progress bar from the xml file
        AnyChartView anyChartView = findViewById(R.id.any_chart_tag);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar_tag));

        Cartesian cartesian = AnyChart.line();

        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // yStroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        // tag = dropdown.getSelectedItem().toString();
        if(dropdown.getSelectedItem() != null){
            tag = dropdown.getSelectedItem().toString();
        } else{
            tag = "";
        }
        cartesian.title("Trend of " + tag);

        cartesian.yAxis(0).title("Rating");
        cartesian.yScale().minimum(0d);
        cartesian.yScale().maximum(10d);
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        List<DataEntry> seriesData = new ArrayList<>();
        //seriesData.add(new ValueDataEntry(df.format(new Date()), 10));
        List<Entry> result = JsonUtils.getEntries("TestFile.json", getApplicationContext());
        // If the database file hasn't been created yet, create it, and make the entry list empty
        if (result == null) {
            result = JsonUtils.createDataFile(TagAnalysisView.this, "TestFile.json");
        }

        result = TagAnalysis.filterEntriesByTag(result,tag);
        Map<Date, Double> entries = TagAnalysis.getAvgRatingByDate(new Date(100,1,1), endDate, result);
        for(Map.Entry<Date, Double> e: entries.entrySet()) {
            seriesData.add(new ValueDataEntry(df.format(e.getKey()), e.getValue()));
        }

        if(tag.equals("")){
            seriesData.add(new ValueDataEntry(df.format(startDate),0));
        }
        Set set = Set.instantiate();
        set.data(seriesData);

        //Mapping seriesMapping = set.mapAs("{ x: 'x', value: 'value' }");

        Line series1 = cartesian.line(seriesData);

        series1.name(tag);
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);
        anyChartView.setChart(cartesian);

        Button renderBtn = (Button) findViewById(R.id.button_go);
        renderBtn.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {

                                             // get the Entries currently in the database
                                             List<Entry> result = JsonUtils.getEntries("TestFile.json", getApplicationContext());
                                             // If the database file hasn't been created yet, create it, and make the entry list empty
                                             if (result == null) {
                                                 result = JsonUtils.createDataFile(TagAnalysisView.this, "TestFile.json");
                                             }
                                             // tag = dropdown.getSelectedItem().toString();
                                             result = TagAnalysis.filterEntriesByTag(result,tag);
                                             List<DataEntry> newseriesData = new ArrayList<>();
                                             //
                                             Map<Date, Double> entries = TagAnalysis.getAvgRatingByDate(startDate, endDate, result);
                                             for(Map.Entry<Date, Double> e: entries.entrySet()) {
                                                 newseriesData.add(new ValueDataEntry(df.format(e.getKey()), e.getValue()));
                                             }
                                             series1.name(tag);
                                             cartesian.title("Trend of " + tag + " from " + df.format(startDate) + " to " + df.format(endDate));

                                             series1.data(newseriesData);
                                         }
                                     }
        );

    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            int year;
            int month;
            int day;
            if(startOrEnd){
                year = startDate.getYear()+1900;
                month = startDate.getMonth();
                day = startDate.getDate();
            } else {
                year = endDate.getYear()+1900;
                month = endDate.getMonth();
                day = endDate.getDate();
            }
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            // a placeholder method to implement the class
            // some weird behavior when I read from the calendar
            year = year - 1900;
            if (startOrEnd) {
                startDate = new Date(year, month, dayOfMonth);
            } else {
                endDate = new Date(year, month, dayOfMonth);
            }
        }
    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    // Inflates options menu from menu_main xml file
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Sets up onClick listeners for each option in the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        // entry log option
        if (id == R.id.action_settings) {

            // Get the entries in the database
            List<Entry> debug = JsonUtils.getEntries("TestFile.json", this);

            // If the database  file has not been created, create it and use empty list
            if (debug == null) {
                debug = JsonUtils.createDataFile(this, "TestFile.json");

            }
            // Change to the entry log activity screen
            Context context = TagAnalysisView.this;
            Class destinationActivity = Entry_log.class;
            Intent logIntent = new Intent(context, destinationActivity);
            startActivity(logIntent);
            return true;

            // entry creation option
        } else if (id == R.id.action_main) {
            // Change to the entry creation activity screen
            Context context = TagAnalysisView.this;
            Class destinationActivity = MainActivity.class;
            Intent mainIntent = new Intent(context, destinationActivity);
            startActivity(mainIntent);
            return true;
            //Rating analysis option
        } else if (id == R.id.action_avg){
            // change to the rating analysis screen
            Context context = TagAnalysisView.this;
            Class destinationActivity = AvgBarGraph.class;
            Intent mainIntent = new Intent(context, destinationActivity);
            startActivity(mainIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}