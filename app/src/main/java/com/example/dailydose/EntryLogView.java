package com.example.dailydose;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

//import com.anychart.core.annotations.Line;
//import com.anychart.scales.Linear;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EntryLogView extends ScrollView {
    /**
     * For this part, we want to create a scrollable column of images. We have extended the
     * ScrollView to accomplish this.
     *
     * Use a single LinearLayout to contain your images, adding margins as necessary to maintain
     * the desired layout. When the device is rotated, the images should fill the column,
     * utilizing the passed vMargin variable to determine spacing.
     *
     * You may not use the LayoutInflater object to accomplish the given layout. You should be
     * creating your layout from scratch using Java.
     *
     * @param context       The context passed from our active activity.
     * @param entries        A list of entries to put in the view.
     */

    public EntryLogView(Context context, List<Entry> entries) {

        super(context);

        LinearLayout scrollView_container = new LinearLayout(context);
        scrollView_container.setOrientation(LinearLayout.VERTICAL);
  
        // To ensure that entries are in the order they were created
        // even after editing
        entries.sort(new Comparator<Entry>() {
            @Override
            public int compare(Entry t1, Entry t2) {
                return t2.getId() - t1.getId();
            }
        });
        
        // Add each entry to the view
        for (int i = 0; i < entries.size(); i++) {
            Entry entry = entries.get(i);    // The Entry
            Typeface typeface = getResources().getFont(R.font.handlee_regular);
            LinearLayout entryLayout = new LinearLayout(context);
            entryLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout dateRating = new LinearLayout(context);
            // The rating
            TextView rating = new TextView(context);
            rating.setTextColor(Color.parseColor("black"));
            rating.setTextSize(20);
            Typeface typeface2 = getResources().getFont(R.font.lobster_regular);
            rating.setTypeface(typeface2);
            //rating.setTypeface(rating.getTypeface(), Typeface.BOLD);
            rating.setText("          " + entry.getRating());

            // The date
            TextView date = new TextView(context);
            date.setTextColor(Color.parseColor("black"));
            date.setTextSize(20);
            date.setTypeface(typeface);
            date.setText("    " + entry.getDate());
            // Putting them together

            dateRating.addView(date);
            dateRating.addView(rating);
            dateRating.setGravity(Gravity.LEFT);

            // The entry's text
            TextView content = new TextView(context);
            content.setText(entry.getContent());
            content.setTextSize(20);
            content.setTextColor(Color.parseColor("black"));

            content.setTypeface(typeface);
            content.setTextAlignment(TEXT_ALIGNMENT_CENTER);

            // Tags
            TextView tagList = new TextView(context);
            tagList.setTextSize(15);
            tagList.setTextColor(Color.parseColor("black"));
            String tags = "(";
            List<String> entryTags = entry.getTags();

            for (int j = 0; j < entryTags.size()-1; j++) {
                tags += entryTags.get(j) + ", ";
            }
            if (!entryTags.isEmpty()) {
                tags += entryTags.get(entryTags.size()-1);
            }
            tags += ")";
            tagList.setText(tags);
            tagList.setTypeface(typeface);

            // Delete Button
            Button deleteButton = new Button(context);
            deleteButton.setText("DELETE");
            deleteButton.setTypeface(typeface);
            deleteButton.setMinimumWidth(70);

            deleteButton.setOnClickListener(new OnClickListener() {
                // Set the delete button to remove this entry from the list and delete it from
                // The database
                @Override
                public void onClick(View view) {
                    JsonUtils.delete(entry.getId(), "TestFile.json", context);
                    scrollView_container.removeView(entryLayout);
                    invalidate();
                }
            });
            // Edit Button
            Button editButton = new Button(context);
            editButton.setText("EDIT");
            editButton.setTypeface(typeface);
            editButton.setOnClickListener(new OnClickListener() {
                // Set the edit button to send the current entry info back to the
                // entry creation activity and switch to that view
                @Override
                public void onClick(View view) {
                    Class destinationActivity = MainActivity.class;
                    Intent editIntent = new Intent(context, destinationActivity);
                    editIntent.putExtra("rating", entry.getRating());
                    editIntent.putExtra("text", entry.getContent());
                    editIntent.putExtra("id", entry.getId());
                    editIntent.putExtra("date", entry.getDate());
                    editIntent.putStringArrayListExtra("tags", (ArrayList<String>) entry.getTags());
                    context.startActivity(editIntent);
                }
            });

            // add all of the entry components to the entry layout
            //entryLayout.addView(rating);
            entryLayout.addView(dateRating);
            entryLayout.addView(content);
            entryLayout.addView(tagList);
            entryLayout.addView(deleteButton);
            entryLayout.addView(editButton);


            // add the entry to the outer linear layout
            scrollView_container.addView(entryLayout);
        }
      // add the linear layout containing all entrie view objects to a scrollview (this)
        addView(scrollView_container);
    }

    public EntryLogView(Context context) {
        this(context, Collections.emptyList());
    }

}
