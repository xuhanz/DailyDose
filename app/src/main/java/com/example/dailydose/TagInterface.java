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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_interface);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        List<String> tagList = new ArrayList<>();

        String entry_text = getIntent().getStringExtra("entry_text");
        int entry_rating = getIntent().getIntExtra("entry_rating", 0);

        Button submit_btn = (Button) findViewById(R.id.submit_button);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChipGroup tags = (ChipGroup) findViewById(R.id.chipGroup);

                // List<Integer> checked_tag_ids = tags.getCheckedChipIds();
                for (int i = 0; i < tags.getChildCount();i++){
                    Chip chip = (Chip)tags.getChildAt(i);
                    if (chip.isChecked()){
                        tagList.add((String) chip.getText());
                    }
                }

                int id = JsonUtils.getHighestID("TestFile.json", getApplicationContext()) + 1;
                Entry new_entry = new Entry(entry_text, entry_rating, id, tagList);
                JsonUtils.writeEntry(new_entry, "TestFile.json", getApplicationContext());

                Context context = TagInterface.this;
                Class destinationActivity = MainActivity.class;
                Intent mainIntent = new Intent(context, destinationActivity);
                startActivity(mainIntent);
            }

        });
    }



}