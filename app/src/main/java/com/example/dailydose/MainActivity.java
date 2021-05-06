package com.example.dailydose;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

	// Get reference to the continue button from xml
	// Field that will store our buttons
	private final Button continue_button = (Button) findViewById(R.id.continue_button);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

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

				startActivity(tagIntent);
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
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}