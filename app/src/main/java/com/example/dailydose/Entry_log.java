package com.example.dailydose;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class Entry_log extends AppCompatActivity {

    private TextView mTextView;

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
        if (id == R.id.action_settings) {
			List<Entry> entries = new ArrayList<>();
			entries.add(new Entry("went to the store", 5.5, 1, new ArrayList<>()));
			entries.add(new Entry("Swimming", 9.0, 2, new ArrayList<>()));
			entries.add(new Entry("Had fun playing video games. Now I am eating a burger and some fries.", 9.0, 3, new ArrayList<>()));
			entries.add(new Entry("went to the store", 5.5, 4, new ArrayList<>()));
			entries.add(new Entry("Swimming", 9.0, 5, new ArrayList<>()));
			entries.add(new Entry("Had fun playing video games. Now I am eating a burger and some fries.", 9.0, 6, new ArrayList<>()));
			entries.add(new Entry("went to the store", 5.5, 7, new ArrayList<>()));
			entries.add(new Entry("Swimming", 9.0, 8, new ArrayList<>()));
			entries.add(new Entry("Had fun playing video games. Now I am eating a burger and some fries.", 9.0, 9, new ArrayList<>()));
			entries.add(new Entry("went to the store", 5.5, 10, new ArrayList<>()));
			entries.add(new Entry("Swimming", 9.0, 11, new ArrayList<>()));
			entries.add(new Entry("Had fun playing video games. Now I am eating a burger and some fries.", 9.0, 12, new ArrayList<>()));
			JsonUtils.writeEntries(entries, "TestFile.json", this);
			Entry edited = JsonUtils.get(1, "TestFile.json", this);
			edited.setRating(11.0);
			JsonUtils.writeEntry(edited, "TestFile.json", this);
            List<Entry> debug = JsonUtils.getEntries("TestFile.json", this);
            setContentView(new EntryLogView(this, debug, 3));
            Toolbar toolbar = findViewById(R.id.header);
            setSupportActionBar(toolbar);
            return true;
        } else if (id == R.id.action_main) {
            setContentView(R.layout.activity_main);
            Toolbar toolbar = findViewById(R.id.header);
            setSupportActionBar(toolbar);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}