package com.example.dailydose;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
     * @param vMargin       The base vertical margin. Passed by the activity.
     */

    @SuppressLint("ResourceType")
    public EntryLogView(Context context, List<Entry> entries, int vMargin) {
        super(context);

       //LinearLayout container = findViewById(R.id.entry_container); // create the parent container for the images
        //addView(container);
        // add it to Part2View
        this.addView(LayoutInflater.from(context).inflate(R.layout.activity_entry_log, null));
        LinearLayout container = findViewById(R.id.entry_container);
        container.setId(0); // set its ID to 0. This lets us refer to it later

        entries.sort(new Comparator<Entry>() {
            @Override
            public int compare(Entry t1, Entry t2) {
                return t1.getId() - t2.getId();
            }
        });

        for (int i = 0; i < entries.size(); i++) {
            Entry entry = entries.get(i);    // The Entry
            LinearLayout entryLayout = new LinearLayout(context);
            entryLayout.setOrientation(LinearLayout.VERTICAL);
            TextView rating = new TextView(context);
            rating.setTextColor(Color.parseColor("purple"));
            rating.setTextSize(20);
            rating.setText("" + entry.getRating());
            TextView content = new TextView(context);
            content.setText(entry.getContent());
            content.setTextSize(20);
            content.setTextAlignment(TEXT_ALIGNMENT_CENTER);
            Button deleteButton = new Button(context);
            deleteButton.setText("DELETE");
            deleteButton.setMinimumWidth(70);
            //ConstraintLayout buttons = new ConstraintLayout(context);

            deleteButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    JsonUtils.delete(entry.getId(), "TestFile.json", context);
                    container.removeView(entryLayout);
                    invalidate();
                }
            });

            Button editButton = new Button(context);
            editButton.setText("EDIT");
            editButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Class destinationActivity = MainActivity.class;
                    Intent editIntent = new Intent(context, destinationActivity);
                    editIntent.putExtra("rating", entry.getRating());
                    editIntent.putExtra("text", entry.getContent());
                    editIntent.putExtra("id", entry.getId());
                    context.startActivity(editIntent);
                }
            });

            //ConstraintSet constraints = new ConstraintSet();
            //deleteButton.setId(i);
            //editButton.setId(i+1);
            //buttons.setId(View.generateViewId());

            entryLayout.addView(rating);
            entryLayout.addView(content);
            entryLayout.addView(deleteButton);
            entryLayout.addView(editButton);
            //entryLayout.addView(buttons);
            container.addView(entryLayout);

            //constraints.connect(deleteButton.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
            //constraints.connect(deleteButton.getId(), ConstraintSet.RIGHT, editButton.getId(), ConstraintSet.LEFT, 0);
            //constraints.connect(editButton.getId(), ConstraintSet.LEFT, deleteButton.getId(), ConstraintSet.RIGHT, 0);
            //constraints.connect(deleteButton.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);

            //constraints.connect(deleteButton.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
            //constraints.connect(deleteButton.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
            //constraints.connect(editButton.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
            //constraints.connect(editButton.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);

            //constraints.addToHorizontalChain(deleteButton.getId(), ConstraintSet.PARENT_ID, editButton.getId());
            //constraints.addToHorizontalChain(editButton.getId(), deleteButton.getId(), ConstraintSet.PARENT_ID);
            //constraints.applyTo(buttons);



        }
    }

    public EntryLogView(Context context) {
        this(context, Collections.emptyList(), 0);
    }
}
