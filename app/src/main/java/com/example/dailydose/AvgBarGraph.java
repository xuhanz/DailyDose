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
import java.util.Dictionary;
import java.util.List;
import java.util.Map;


public class AvgBarGraph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avg_rating_analysis);

        setSupportActionBar(findViewById(R.id.header_avg));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        AnyChartView anyChartView = findViewById(R.id.any_chart_avg);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Cartesian cartesian = AnyChart.column();

        List<Entry> result = JsonUtils.getEntries("TestFile.json", getApplicationContext());

        if (result == null) {
            result = JsonUtils.createDataFile(this, "TestFile.json");
        }

        Map<String, Double> avgRating = (Map<String, Double>) TagAnalysis.getAllTagAvg(result);

        List<DataEntry> data = new ArrayList<>();
        for(Map.Entry<String,Double> e: avgRating.entrySet()) {
            data.add(new ValueDataEntry(e.getKey(), e.getValue()));
        }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        // entry log option
        if (id == R.id.action_settings) {
            List<Entry> debug = JsonUtils.getEntries("TestFile.json", this);

            if (debug == null) {
                debug = new ArrayList<>();
                List<Entry> entries = new ArrayList<>();
                entries.add(new Entry("went to the store", 5, 1000, new ArrayList<>()));
                JsonUtils.writeEntries(entries, "TestFile.json", this);
                JsonUtils.delete(1000, "TestFile.json", this);

            }

            //setContentView(new EntryLogView(this, debug, 3));
            Context context = AvgBarGraph.this;
            Class destinationActivity = Entry_log.class;
            Intent logIntent = new Intent(context, destinationActivity);
            startActivity(logIntent);

            //Toolbar toolbar = findViewById(R.id.header);
            //setSupportActionBar(toolbar);
            //getSupportActionBar().setDisplayShowTitleEnabled(false);

            return true;
            // entry creation option
        } else if (id == R.id.action_main) {
            Context context = AvgBarGraph.this;
            Class destinationActivity = MainActivity.class;
            Intent mainIntent = new Intent(context, destinationActivity);
            startActivity(mainIntent);
            return true;
        } else if (id == R.id.action_avg){
            Context context = AvgBarGraph.this;
            Class destinationActivity = AvgBarGraph.class;
            Intent mainIntent = new Intent(context, destinationActivity);
            startActivity(mainIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}