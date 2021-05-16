package com.example.dailydose;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class Entry_log extends AppCompatActivity {


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

            Context context = Entry_log.this;
            Class destinationActivity = Entry_log.class;
            Intent logIntent = new Intent(context, destinationActivity);
            startActivity(logIntent);
            return true;
            // entry creation option
        } else if (id == R.id.action_main) {
            Context context = Entry_log.this;
            Class destinationActivity = MainActivity.class;
            Intent mainIntent = new Intent(context, destinationActivity);
            startActivity(mainIntent);
            return true;
        } else if (id == R.id.action_avg){
            Context context = Entry_log.this;
            Class destinationActivity = AvgBarGraph.class;
            Intent mainIntent = new Intent(context, destinationActivity);
            startActivity(mainIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_log);

        setSupportActionBar(findViewById(R.id.header_log));

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        LinearLayout container = findViewById(R.id.scrollView_container);
        container.addView(new EntryLogView(this, JsonUtils.getEntries("TestFile.json", getApplicationContext())));

    }
}