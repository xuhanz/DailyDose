package com.example.dailydose;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.view.View.OnClickListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

	// Get reference to the continue button from xml
	// Field that will store our buttons
	//private final Button continue_button = (Button) findViewById(R.id.continue_button);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = findViewById(R.id.header);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		Button continue_button = (Button) findViewById(R.id.continue_button);


		// Set an onClickListener to allow the continue button to trigger the tagging interface
		// activity
		continue_button.setOnClickListener(new OnClickListener() {

			/**
			 * The onClick method is triggered when continue button is clicked.
			 *
			 * @param v The view that is clicked, which is the continue_button
			 */
			@Override
			public void onClick(View v) {
				Context context = MainActivity.this;

				// This is the class that we want to start (and open) when the button is clicked.
				Class destinationActivity = TagInterface.class;

				/*
				 * We create the Intent that will start the Activity we specified above in
				 * the destinationActivity variable. The constructor for an Intent also requires a
				 * context, which we stored in the variable named "context".
				 */
				Intent tagIntent = new Intent(context, destinationActivity);
				EditText entry = findViewById(R.id.entry_text);
				Editable entry_text = entry.getText();
				tagIntent.putExtra("entry_text", entry_text);

				SeekBar slider = findViewById(R.id.seekBar);
				int rating = slider.getProgress();
				tagIntent.putExtra("entry_rating", rating);

				startActivity(tagIntent);
			}
		});

		Button discard_btn = (Button) findViewById(R.id.discard_button);
		discard_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				SeekBar slider = findViewById(R.id.seekBar);
				slider.setProgress(1);
				EditText entry_text = findViewById(R.id.entry_text);
				entry_text.clearComposingText();
			}
		});

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
		if (id == R.id.action_settings) {
			List<Entry> entries = JsonUtils.getEntries("TestFile.json", this);
			setContentView(new EntryLogView(this, entries, 3));
			Toolbar toolbar = findViewById(R.id.header);
			setSupportActionBar(toolbar);
			return true;
		} else if (id == R.id.action_main) {
			setContentView(R.layout.activity_main);
			Toolbar toolbar = findViewById(R.id.header);
			setSupportActionBar(toolbar);
			return true;
		} else if (id == R.id.action_avg){
			setContentView(R.layout.avg_rating_analysis);
			Toolbar toolbar = findViewById(R.id.header);
			setSupportActionBar(toolbar);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}