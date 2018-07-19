package com.example.kathrinegibson.planterbox;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Switch;

import static com.example.kathrinegibson.planterbox.MainActivity.rcAdapter;
import static com.example.kathrinegibson.planterbox.MainActivity.userAddedPlants;


public class AddActivity extends Activity implements OnItemSelectedListener {
    PlantType newPlantType = PlantType.Default;
    String plantName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        final Switch outdoorSwitch = findViewById(R.id.switch1);

        Button addButton = findViewById(R.id.add_plant_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userInput = findViewById(R.id.editText1);
                plantName = userInput.getText().toString();
                if(!plantName.equals("") && (newPlantType != PlantType.Default)){
                    //if there is valid input, add the new plant to the list of plants
                    Plant newPlant = new Plant(newPlantType);
                    newPlant.changePlantName(plantName);
                    if(outdoorSwitch.isChecked()){
                        newPlant.changeOutdoorPlant(true);
                        System.out.println("when added: " + newPlant.getOutdoorPlant());
                    }
                    userAddedPlants.add(newPlant);
                    IOUtility.SaveData(AddActivity.this, userAddedPlants);
                    rcAdapter.notifyItemInserted(userAddedPlants.size() - 1);
                }
                finish();
            }
        });

        //spinner is the drop down menu
        Spinner spinner = findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(this);

        // Creating adapter for spinner
        ArrayAdapter<PlantType> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, PlantType.values());

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        newPlantType = PlantType.values()[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
