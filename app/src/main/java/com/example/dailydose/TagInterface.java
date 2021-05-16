package com.example.dailydose;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class TagInterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Change the screen to be the tagging screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_interface);

        // Set the toolbar to be the one in the xml file
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        List<String> tagList = new ArrayList<>();

        // Get the state from the entry creation page
        String entry_text = getIntent().getStringExtra("entry_text");
        int entry_rating = getIntent().getIntExtra("entry_rating", 0);
        int entry_id = getIntent().getIntExtra("id", 0);

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
                        tagList.add((String) chip.getText());
                    }
                }
                int id;
                if (entry_id == 0) { // creating a new entry
                    id = JsonUtils.getHighestID("TestFile.json", getApplicationContext()) + 1;
                } else { // editing existing entries
                    id = entry_id;
                }

                // Make an entry based on passed in state + selected tags
                Entry new_entry = new Entry(entry_text, entry_rating, id, tagList);
                // Write the entry
                JsonUtils.writeEntry(new_entry, "TestFile.json", getApplicationContext());

                // Return to the entry creation screen
                Context context = TagInterface.this;
                Class destinationActivity = MainActivity.class;
                Intent mainIntent = new Intent(context, destinationActivity);
                startActivity(mainIntent);
            }

        });
    }



}