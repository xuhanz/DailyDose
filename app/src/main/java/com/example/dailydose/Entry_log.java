package com.example.dailydose;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Entry_log extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Entry> entries = JsonUtils.getEntries("TestFile.json", this);
        setContentView(new EntryLogView(this, entries, 3));

        setContentView(R.layout.activity_entry_log);





    }
}