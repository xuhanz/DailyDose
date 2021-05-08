package com.example.dailydose;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		/*
		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});

		 */
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
			/*List<Entry> entries = new ArrayList<>();
			entries.add(new Entry("went to the store", 5.5, 1, new ArrayList<>()));
			entries.add(new Entry("Swimming", 9.0, 2, new ArrayList<>()));
			entries.add(new Entry("Had fun playing video games. Now I am eating a burger and some fries.", 9.0, 2, new ArrayList<>()));
			JsonUtils.writeEntries(entries, "TestFile.json", this);
			Entry edited = JsonUtils.get(1, "TestFile.json", this);
			edited.setRating(11.0);
			JsonUtils.writeEntry(edited, "TestFile.json", this);
*/
			List<Entry> debug = JsonUtils.getEntries("TestFile.json", this);
			setContentView(new EntryLogView(this, debug, 3));
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}