package com.example.dailydose;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.Collections;
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
     * @param vMargin       The base vertical margin. Passed by the activity.
     */

    public EntryLogView(Context context, List<Entry> entries, int vMargin) {
        super(context);

       //LinearLayout container = findViewById(R.id.entry_container); // create the parent container for the images
        //addView(container);
        // add it to Part2View
        this.addView(LayoutInflater.from(context).inflate(R.layout.activity_entry_log, null));
        LinearLayout container = findViewById(R.id.entry_container);
        container.setId(0); // set its ID to 0. This lets us refer to it later


        for (int i = 0; i < entries.size(); i++) {
            Entry entry = entries.get(i);    // The Entry
            LinearLayout entryLayout = new LinearLayout(context);
            entryLayout.setOrientation(LinearLayout.VERTICAL);
            TextView rating = new TextView(context);
            rating.setTextColor(Color.parseColor("purple"));
            rating.setTextSize(30);
            rating.setText("" + entry.getRating());
            TextView content = new TextView(context);
            content.setText(entry.getContent());
            content.setTextSize(20);
            content.setTextAlignment(TEXT_ALIGNMENT_CENTER);
            Button deleteButton = new Button(context);
            deleteButton.setText("DELETE");
            deleteButton.setMinimumWidth(100);
            deleteButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    JsonUtils.delete(entry.getId(), "TestFile.json", context);
                    container.removeView(entryLayout);
                    invalidate();
                }
            });
            //Button editButton = new Button(context);
            //editButton.setText("EDIT");
            //editButton.setMinimumWidth(100);


            entryLayout.addView(rating);
            entryLayout.addView(content);
            entryLayout.addView(deleteButton);
           // entryLayout.addView(editButton);

            container.addView(entryLayout);


        }
    }

    public EntryLogView(Context context) {
        this(context, Collections.emptyList(), 0);
    }
}
