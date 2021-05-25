package com.example.dailydose;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.anychart.APIlib;
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
import java.util.Comparator;
import java.util.Date;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;


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
        AnyChartView anyChartView_top = findViewById(R.id.any_chart_avg_top);
        anyChartView_top.setProgressBar(findViewById(R.id.progress_bar_top));
        AnyChartView anyChartView_low = findViewById(R.id.any_chart_avg_low);
        anyChartView_low.setProgressBar(findViewById(R.id.progress_bar_low));

        Cartesian cartesian = AnyChart.column();
        Cartesian cartesian_top = AnyChart.column();
        Cartesian cartesian_low = AnyChart.column();

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
        List<DataEntry> data_top = new ArrayList<>();
        List<DataEntry> data_low = new ArrayList<>();
        for(Map.Entry<String,Double> e: avgRating.entrySet()) {
            data.add(new ValueDataEntry(e.getKey(), e.getValue()));
        }

        TreeSet<Map.Entry<String, Double>> avgRatingTop = new TreeSet<>(new Comparator<Map.Entry<String, Double>>()
        {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                int valueComparison = o1.getValue().compareTo(o2.getValue());
                return valueComparison == 0 ? o1.getKey().compareTo(o2.getKey()) : valueComparison;
            }
        });

        TreeSet<Map.Entry<String, Double>> avgRatingLow = new TreeSet<>(new Comparator<Map.Entry<String, Double>>()
        {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                int valueComparison = o2.getValue().compareTo(o1.getValue());
                return valueComparison == 0 ? o2.getKey().compareTo(o1.getKey()) : valueComparison;
            }
        });


        // sort the entries by value
        for(Map.Entry<String,Double> e: avgRating.entrySet()) {
            avgRatingTop.add(e);
            avgRatingLow.add(e);
        }
        int i = 0;
        Iterator<Map.Entry<String, Double>> itr_top = avgRatingTop.iterator();
        Iterator<Map.Entry<String, Double>> itr_low = avgRatingLow.iterator();
        // number of tags to display
        while(itr_top.hasNext() && i < 3){
            Map.Entry<String, Double> nextTop = itr_top.next();
            data_top.add(new ValueDataEntry(nextTop.getKey(),nextTop.getValue()));
            Map.Entry<String, Double> nextLow = itr_low.next();
            data_low.add(new ValueDataEntry(nextLow.getKey(),nextLow.getValue()));
            i ++;
        }


        // Set up the average ratings for all tags
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

        // Set up the average ratings for top rating tags
        Column column_top = cartesian.column(data_top);

        column_top.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }");

        cartesian_top.animation(true);
        cartesian_top.title("Top rating tags");

        cartesian_top.yScale().minimum(0d);

        cartesian_top.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

        cartesian_top.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian_top.interactivity().hoverMode(HoverMode.BY_X);

        cartesian_top.xAxis(0).title("Tag");
        cartesian_top.yAxis(0).title("Rating");

        anyChartView_top.setChart(cartesian_top);

        // Set up the average ratings for low rating tags
        Column column_low = cartesian.column(data_low);

        column_low.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }");

        cartesian_low.animation(true);
        cartesian_low.title("Lowest rating tags");

        cartesian_low.yScale().minimum(0d);

        cartesian_low.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

        cartesian_low.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian_low.interactivity().hoverMode(HoverMode.BY_X);

        cartesian_low.xAxis(0).title("Tag");
        cartesian_low.yAxis(0).title("Rating");

        anyChartView_low.setChart(cartesian_low);
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
        } else if (id == R.id.action_tag){
            // Switch to the Tag analysis graph screen
            Context context = AvgBarGraph.this;
            Class destinationActivity = TagAnalysisView.class;
            Intent mainIntent = new Intent(context, destinationActivity);
            startActivity(mainIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}