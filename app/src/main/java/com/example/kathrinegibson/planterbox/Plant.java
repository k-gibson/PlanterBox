package com.example.kathrinegibson.planterbox;

import org.parceler.Parcel;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

//in order to implement parcelable (so that plants can be passed between activities using intents)
@Parcel
public class Plant {
    String name;
    int imagePath;
    PlantType plantType;
    Date plantDate = new Date();
    Timestamp timeCreated = new Timestamp(plantDate.getTime());
    Date wateredDate = new Date();
    Timestamp lastWatered = new Timestamp(wateredDate.getTime());
    //Timer plantTimer = new Timer();
    Boolean outdoorPlant;

    // empty constructor needed by the Parceler library
    public Plant(){

    }

    public Plant(String name, int imagePath, PlantType plantType, Boolean outdoorPlant) { //, Timer plantTimer
        this.name = name;
        this.imagePath = imagePath;
        this.plantType = plantType;
        //this.plantTimer = plantTimer;
        this.outdoorPlant = outdoorPlant;
    }

    public Plant(PlantType plantType) {
        for(Plant p : possiblePlants){
            if(p.plantType == plantType){
                this.name = "";
                this.imagePath = p.imagePath;
                this.plantType = plantType;
                //this.plantTimer = p.plantTimer;
                this.outdoorPlant = false;
                return;
            }
        }

        this.name = null;
        this.imagePath = -1;
        this.plantType = PlantType.Default;
        //this.plantTimer = null;
        this.outdoorPlant = false;
    }

    static private ArrayList<Plant> possiblePlants = new ArrayList<>();

    //sets up all the possibilities for plants
    static {
        possiblePlants.add(new Plant(null, R.drawable.aloe, PlantType.AloeVera, null)); //, false));
        possiblePlants.add(new Plant(null, R.drawable.echeveria, PlantType.Echeveria, null)); //, false));
        possiblePlants.add(new Plant(null, R.drawable.iris, PlantType.Iris, null));
        possiblePlants.add(new Plant(null, R.drawable.orchid, PlantType.Orchid, null));
        possiblePlants.add(new Plant(null, R.drawable.snakeplant, PlantType.SnakePlant, null));
        possiblePlants.add(new Plant(null, R.drawable.zzplant, PlantType.ZzPlant, null));
    }

    public void changePlantName(String newName){
        this.name = newName;
    }

    public String getName(){
        return "\"" + name + "\"";
    }

    public int getImagePath(){
        return imagePath;
    }

    public PlantType getPlantType() {
        return plantType;
    }

    public String getPlantTypeString(){
        return plantType.toString();
    }

    public Boolean getOutdoorPlant(){
        return  outdoorPlant;
    }

    public void changeOutdoorPlant(Boolean newPlace){
        this.outdoorPlant = newPlace;
    }

    public String getTimeCreatedString() {
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(plantDate);
    }

    public void updateLastWatered(){
        wateredDate = new Date();
        lastWatered = new Timestamp(wateredDate.getTime());
    }

    public String getLastWateredString(){
        String pattern = "EEE MM-dd hh:mm aa";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(wateredDate);
    }

    private void updateWaterTimer(){

    }
}