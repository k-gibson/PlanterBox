package com.example.kathrinegibson.planterbox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;
import org.parceler.Parcels;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private ArrayList<Plant> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<Plant> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolders holder, final int position) {
        holder.plantName.setText(itemList.get(position).getName());
        holder.plantType.setText(itemList.get(position).getPlantTypeString());
        holder.plantPhoto.setImageResource(itemList.get(position).getImagePath());
        final Intent editPlantIntent = new Intent(context, EditActivity.class).putExtra("my plant position", position);
        final Intent displayPlantIntent = new Intent(context, DisplayActivity.class).putExtra("my plant", Parcels.wrap(itemList.get(position)));
        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(context, holder.buttonViewOption);
                //inflating menu from xml resource
                popup.inflate(R.menu.plant_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                itemList.get(position).updateLastWatered();
                                IOUtility.SaveData((Activity) context, itemList);
                                notifyItemChanged(position);
                                break;
                            case R.id.menu2:
                                context.startActivity(displayPlantIntent);
                                break;
                            case R.id.menu3:
                                context.startActivity(editPlantIntent);
                                notifyItemChanged(position);
                                break;
                            case R.id.menu4:
                                itemList.remove(position);
                                IOUtility.SaveData((Activity) context, itemList);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position,itemList.size());
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }


}
