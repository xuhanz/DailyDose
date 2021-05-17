package com.example.dailydose;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.zip.Inflater;

public class TagInterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Change the screen to be the tagging screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_interface);

        // Set the toolbar to be the one in the xml file
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        List<String> selected_tagList = new ArrayList<>();

        // Get the state from the entry creation page
        String entry_text = getIntent().getStringExtra("entry_text");
        int entry_rating = getIntent().getIntExtra("entry_rating", 0);
        int entry_id = getIntent().getIntExtra("id", 0);


        String entry_date = getIntent().getStringExtra("entry_date");
        // Submit button
        Button submit_btn = (Button) findViewById(R.id.submit_button);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChipGroup tags = (ChipGroup) findViewById(R.id.chipGroup);

                // loop through each chip (tag) and if it is checked, add to the list
                // of tags applied to this entry
                for (int i = 0; i < tags.getChildCount();i++){
                    Chip chip = (Chip)tags.getChildAt(i);
                    if (chip.isChecked()){
                        selected_tagList.add((String) chip.getText());
                    }
                }
                int id;
                if (entry_id == 0) { // creating a new entry
                    id = JsonUtils.getHighestID("TestFile.json", getApplicationContext()) + 1;
                } else { // editing existing entries
                    id = entry_id;
                }

                // Make an entry based on passed in state + selected tags
                Entry new_entry = new Entry(entry_text, entry_rating, id, selected_tagList, entry_date);
                // Write the entry
                JsonUtils.writeEntry(new_entry, "TestFile.json", getApplicationContext());

                // Return to the entry creation screen
                Context context = TagInterface.this;
                Class destinationActivity = MainActivity.class;
                Intent mainIntent = new Intent(context, destinationActivity);
                startActivity(mainIntent);
            }

        });

        // add custom tag button
        FloatingActionButton addTagButton = findViewById(R.id.add_tag_button);
        addTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddTagDialog(TagInterface.this);
            }
        });

        // generating chips
        ChipGroup tags = findViewById(R.id.chipGroup);
        Set<String> tagList = JsonUtils.getAllTags("TestFile.json", this);
        for (String tag : tagList) {
            Chip newTag = (Chip) getLayoutInflater().inflate(R.layout.chip_layout, tags, false);
            newTag.setText(tag);
            newTag.setChipBackgroundColorResource(R.color.yellow_02);
            tags.addView(newTag);
        }

    }

    private void showAddTagDialog(Context c) {
        final EditText taskEditText = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Add a new tag")
                .setMessage("What do you want to add?")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tag = String.valueOf(taskEditText.getText());
                        ChipGroup tags = findViewById(R.id.chipGroup);
                        Chip newTag = (Chip) getLayoutInflater().inflate(R.layout.chip_layout, tags, false);
                        newTag.setText(tag);
                        newTag.setChipBackgroundColorResource(R.color.yellow_02);
                        tags.addView(newTag);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }



}