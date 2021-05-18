package com.example.dailydose;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class TagAnalysisView extends AppCompatActivity {

    private static boolean startOrEnd = true;
    private static int startY = Calendar.YEAR;
    private static int startM = Calendar.MONTH;
    private static int startD = Calendar.DATE;
    private static int endY = Calendar.YEAR;
    private static int endM = Calendar.MONTH;
    private static int endD = Calendar.DATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the view to be the avg_rating_analysis xml file
        setContentView(R.layout.activity_tag_analysis);

        // Set the support action bar to be the one defined in the xml file,
        // includes menu
        setSupportActionBar(findViewById(R.id.header_tag));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Button startDateBtn = (Button) findViewById(R.id.button_start_date);
        Button endDateBtn = (Button) findViewById(R.id.button_end_date);

        startDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
                startOrEnd = true;
            }
        });

        endDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
                startOrEnd = false;
            }
        });

        Set<String> tags = JsonUtils.getAllUsedTags("TestFile.json", this);
        String[] tags_arr = new String[tags.size()];
        tags_arr = tags.toArray(tags_arr);

        Spinner dropdown = (Spinner) findViewById(R.id.tag_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tags_arr);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        dropdown.setAdapter(adapter);


        //Get the anyChartView and Progress bar from the xml file
        AnyChartView anyChartView = findViewById(R.id.any_chart_tag);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar_tag));

        Cartesian cartesian = AnyChart.column();

        // get the Entries currently in the database
        List<Entry> result = JsonUtils.getEntries("TestFile.json", getApplicationContext());

        // If the database file hasn't been created yet, create it, and make the entry list empty
        if (result == null) {
            result = JsonUtils.createDataFile(this, "TestFile.json");
        }

    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            // a placeholder method to implement the class
            if (startOrEnd) {
                startY = year;
                // for some reason the month is 0 based
                startM = month + 1;
                startD = dayOfMonth;
            } else {
                endY = year;
                // for some reason the month is 0 based
                endM = month + 1;
                endD = dayOfMonth;
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