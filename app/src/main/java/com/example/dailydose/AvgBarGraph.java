package com.example.dailydose;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;


public class AvgBarGraph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the view to be the avg_rating_analysis xml file
        setContentView(R.layout.avg_rating_analysis);

        // Set the support action bar to be the one defined in the xml file,
        // includes menu
        setSupportActionBar(findViewById(R.id.header_avg));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Get the anyChartView and Progress bar from the xml file
        AnyChartView anyChartView = findViewById(R.id.any_chart_avg);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Cartesian cartesian = AnyChart.column();

        // get the Entries currently in the database
        List<Entry> result = JsonUtils.getEntries("TestFile.json", getApplicationContext());

        // If the database file hasn't been created yet, create it, and make the entry list empty
        if (result == null) {
            result = JsonUtils.createDataFile(this, "TestFile.json");
        }

        // Get the avg rating of each tag
        Map<String, Double> avgRating = (Map<String, Double>) TagAnalysis.getAllTagAvg(result);

        // Convert the tags and their average ratings to DataEntry objects and put them in
        // a list to be used to create the graph
        List<DataEntry> data = new ArrayList<>();
        for(Map.Entry<String,Double> e: avgRating.entrySet()) {
            data.add(new ValueDataEntry(e.getKey(), e.getValue()));
        }


        // Set up the Graph
        Column column = cartesian.column(data);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }");

        cartesian.animation(true);
        cartesian.title("Average Rating of Tags");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Tag");
        cartesian.yAxis(0).title("Rating");

        anyChartView.setChart(cartesian);
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
            Context context = AvgBarGraph.this;
            Class destinationActivity = Entry_log.class;
            Intent logIntent = new Intent(context, destinationActivity);
            startActivity(logIntent);
            return true;

            // entry creation option
        } else if (id == R.id.action_main) {
            // Change to the entry creation activity screen
            Context context = AvgBarGraph.this;
            Class destinationActivity = MainActivity.class;
            Intent mainIntent = new Intent(context, destinationActivity);
            startActivity(mainIntent);
            return true;
            //Rating analysis option
        } else if (id == R.id.action_avg){
            // change to the rating analysis screen
            Context context = AvgBarGraph.this;
            Class destinationActivity = AvgBarGraph.class;
            Intent mainIntent = new Intent(context, destinationActivity);
            startActivity(mainIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}