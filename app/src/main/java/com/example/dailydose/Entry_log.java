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
            // Get the list of entries in the database
            List<Entry> debug = JsonUtils.getEntries("TestFile.json", this);

            //If the database file has not been created, create it
            if (debug == null) {
                debug = JsonUtils.createDataFile(this, "TestFile.json");

            }

            // Change to the entry log activity screen
            Context context = Entry_log.this;
            Class destinationActivity = Entry_log.class;
            Intent logIntent = new Intent(context, destinationActivity);
            startActivity(logIntent);
            return true;
            // entry creation option
        } else if (id == R.id.action_main) {
            // Change to the entry creation activity screen
            Context context = Entry_log.this;
            Class destinationActivity = MainActivity.class;
            Intent mainIntent = new Intent(context, destinationActivity);
            startActivity(mainIntent);
            return true;
            // Rating analysis option
        } else if (id == R.id.action_avg){
            // Change to the rating analysis page
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
        // Set the view to be to the entry log
        setContentView(R.layout.activity_entry_log);

        //Set the toolbar
        setSupportActionBar(findViewById(R.id.header_log));

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Create an EntryLogView with the current Entries in the database
        LinearLayout container = findViewById(R.id.scrollView_container);
        container.addView(new EntryLogView(this, JsonUtils.getEntries("TestFile.json", getApplicationContext())));

    }
}