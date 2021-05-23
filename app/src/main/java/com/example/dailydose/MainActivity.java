package com.example.dailydose;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.format.DateFormat;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.view.View.OnClickListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private int id = 0;
	private String date;
	// Get reference to the continue button from xml
	// Field that will store our buttons
	//private final Button continue_button = (Button) findViewById(R.id.continue_button);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set the current screen to be the entry creation activity screen
		setContentView(R.layout.activity_main);
		Toolbar toolbar = findViewById(R.id.header);
		// Set the top bar to be the toolbar with the menu inside
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);

		// if this is an edit and the info should be filled in, then there will be
		// "extras" in the intent. fill in the info
		if(getIntent().hasExtra("rating")) {
			SeekBar slider = findViewById(R.id.seekBar);
			slider.setProgress((int)getIntent().getDoubleExtra("rating", 1));
			TextView progress = findViewById(R.id.textView3);
			progress.setText("" + (int)getIntent().getDoubleExtra("rating", 1));
			EditText entry_text = findViewById(R.id.entry_text);
			entry_text.setText(getIntent().getStringExtra("text"));
			id = getIntent().getIntExtra("id", 0);
			date = getIntent().getStringExtra("date");
			// Else, denote with id 0 (which is impossible) to indicate this is new and
			// needs an id assigned to it
		} else {
			id = 0;
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			date = df.format(new Date());
		}

		// Continue button
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
				tagIntent.putExtra("entry_text", entry_text.toString());
				tagIntent.putExtra("id", id);

				SeekBar slider = findViewById(R.id.seekBar);
				int rating = slider.getProgress();
				tagIntent.putExtra("entry_rating", rating);
				tagIntent.putExtra("entry_date", date);
				startActivity(tagIntent);
			}
		});

		// Discard Button
		Button discard_btn = (Button) findViewById(R.id.discard_button);
		discard_btn.setOnClickListener(new OnClickListener() {
			// Set the discard button to clear the text box and put the rating slider back to its
			// default position of 1
			@Override
			public void onClick(View view) {
				SeekBar slider = findViewById(R.id.seekBar);
				slider.setProgress(1);
				EditText entry_text = findViewById(R.id.entry_text);
				entry_text.setText("");
			}
		});

		// Progress Bar
		SeekBar ratingBar = findViewById(R.id.seekBar);
		ratingBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
				TextView progress = findViewById(R.id.textView3);
				progress.setText("" + i);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				return;
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				return;
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
		// entry log option
		if (id == R.id.action_settings) {
			// Get the entries currently in the database
			List<Entry> debug = JsonUtils.getEntries("TestFile.json", this);

			// if the database file has not been created, create it
			if (debug == null) {
				debug = JsonUtils.createDataFile(this, "TestFile.json");
			}

			// switch to the entry log screen
			Context context = MainActivity.this;
			Class destinationActivity = Entry_log.class;
			Intent logIntent = new Intent(context, destinationActivity);
			startActivity(logIntent);

			return true;
			// entry creation option
		} else if (id == R.id.action_main) {
			// Switch to the entry creation screen
			Context context = MainActivity.this;
			Class destinationActivity = MainActivity.class;
			Intent mainIntent = new Intent(context, destinationActivity);
			startActivity(mainIntent);
			return true;

			// Tag analysis option
		} else if (id == R.id.action_avg){

			// Switch to the avg analysis graph screen
			Context context = MainActivity.this;
			Class destinationActivity = AvgBarGraph.class;
			Intent mainIntent = new Intent(context, destinationActivity);
			startActivity(mainIntent);
			return true;
		} else if (id == R.id.action_tag){
			// Switch to the Tag analysis graph screen
			Context context = MainActivity.this;
			Class destinationActivity = TagAnalysisView.class;
			Intent mainIntent = new Intent(context, destinationActivity);
			startActivity(mainIntent);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}