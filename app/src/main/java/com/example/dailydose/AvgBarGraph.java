package com.example.dailydose;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        AnyChartView anyChartView = findViewById(R.id.any_chart_avg);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Cartesian cartesian = AnyChart.column();

        List<Entry> result = JsonUtils.getEntries("TestFile.json", getApplicationContext());
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
}