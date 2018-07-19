package com.example.kathrinegibson.planterbox;


import android.app.Activity;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class IOUtility {

    static String KEY = "plant list";

    //load up list of plants when starting the app
    public static ArrayList<Plant> LoadData(Activity activity){
        ArrayList<Plant> plants;
        SharedPreferences sharedPreferences = activity.getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY, null);
        Type type = new TypeToken<ArrayList<Plant>>() {}.getType();
        plants = gson.fromJson(json, type);

        if (plants == null){
            plants = new ArrayList<>();
        }
        return plants;
    }

    //update the list of plants when adding a plant
    public static void SaveData(Activity activity, ArrayList<Plant> plantList){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(plantList);

        editor.putString(KEY, json);
        editor.apply();
    }
}