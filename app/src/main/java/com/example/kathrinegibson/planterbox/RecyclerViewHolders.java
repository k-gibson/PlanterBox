package com.example.kathrinegibson.planterbox;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewHolders extends RecyclerView.ViewHolder{

    public TextView plantName;
    public TextView plantType;
    public ImageView plantPhoto;
    public TextView buttonViewOption;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        plantName = itemView.findViewById(R.id.plant_name);
        plantType = itemView.findViewById(R.id.plant_type);
        plantPhoto = itemView.findViewById(R.id.plant_photo);
        buttonViewOption = itemView.findViewById(R.id.textViewOptions);
    }
}
