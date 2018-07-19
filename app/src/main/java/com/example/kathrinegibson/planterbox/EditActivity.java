package com.example.kathrinegibson.planterbox;


import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import static com.example.kathrinegibson.planterbox.MainActivity.rcAdapter;
import static com.example.kathrinegibson.planterbox.MainActivity.userAddedPlants;

public class EditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    PlantType newPlantType = PlantType.Default;
    String plantName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final int position = getIntent().getIntExtra("my plant position", 0);

        final Plant editPlant = userAddedPlants.get(position);

        if (editPlant.getPlantType() != null){
            newPlantType = editPlant.getPlantType();
        }
        else{
            newPlantType = PlantType.Default;
        }
        plantName = editPlant.getName().replaceAll("^\"|\"$", "");
        TextView oldName = findViewById(R.id.editText1);
        oldName.setText(plantName);

        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final Switch outdoorSwitch = findViewById(R.id.switch2);
        if(editPlant.getOutdoorPlant()){
            outdoorSwitch.setChecked(true);
        }

        Button saveButton = findViewById(R.id.save_plant_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userInput = findViewById(R.id.editText1);
                plantName = userInput.getText().toString();
                if(!plantName.equals("") && (newPlantType != PlantType.Default)){
                    //if there is valid input, edit the plant
                    Plant newPlant = new Plant(newPlantType);
                    newPlant.changePlantName(plantName);
                    if(outdoorSwitch.isChecked()){
                        newPlant.changeOutdoorPlant(true);
                    }

                    if (editPlant != newPlant){
                        userAddedPlants.set(position, newPlant);
                        IOUtility.SaveData(EditActivity.this, userAddedPlants);
                        rcAdapter.notifyItemChanged(position);
                        finish();
                    }
                }
                else {
                    finish();
                }
            }
        });

        //spinner is the drop down menu
        Spinner spinner = findViewById(R.id.spinner2);
        spinner.setOnItemSelectedListener(this);

        // Creating adapter for spinner
        ArrayAdapter<PlantType> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, PlantType.values());

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        int spinnerPosition = dataAdapter.getPosition(editPlant.getPlantType());
        spinner.setSelection(spinnerPosition);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        newPlantType = PlantType.values()[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
