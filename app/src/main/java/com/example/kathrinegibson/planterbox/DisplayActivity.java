package com.example.kathrinegibson.planterbox;

import android.annotation.SuppressLint;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import org.parceler.Parcels;

public class DisplayActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        final Plant editPlant = Parcels.unwrap(getIntent().getParcelableExtra("my plant"));
        TextView nameText = findViewById(R.id.plant_name);
        TextView typeText = findViewById(R.id.plant_type);
        TextView timeCreated = findViewById(R.id.plant_creation);
        TextView lastWatered = findViewById(R.id.plant_watered);
        TextView outdoorPlant = findViewById(R.id.if_outside);
        ImageView imagePath = findViewById(R.id.plant_photo);


        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        nameText.setText(editPlant.getName());
        typeText.setText(editPlant.getPlantTypeString());
        timeCreated.setText(editPlant.getTimeCreatedString());
        lastWatered.setText(editPlant.getLastWateredString());
        imagePath.setImageResource(editPlant.getImagePath());
        if(editPlant.getOutdoorPlant()){
            outdoorPlant.setText("Your plant is outdoors");
        } else{
            outdoorPlant.setText("Your plant is indoors");
        }

        Button doneButton = findViewById(R.id.done_plant_button);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
