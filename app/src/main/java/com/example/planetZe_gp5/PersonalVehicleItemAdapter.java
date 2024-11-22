package com.example.planetZe_gp5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonalVehicleItemAdapter extends RecyclerView.Adapter<PersonalVehicleItemAdapter.PersonalVehicleItemViewHolder> {
    private List<PersonalVehicleItem> itemList;

    public PersonalVehicleItemAdapter(List<PersonalVehicleItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public PersonalVehicleItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_adapater, parent, false);
        return new PersonalVehicleItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalVehicleItemViewHolder holder, int position) {
        PersonalVehicleItem item = itemList.get(position);
        holder.textViewDistanceDriven.setText(item.getDistanceDriven());
        holder.textViewVehicleType.setText(item.getVehicleType());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class PersonalVehicleItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDistanceDriven, textViewVehicleType;

        public PersonalVehicleItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDistanceDriven = itemView.findViewById(R.id.textViewDistanceDriven);
            textViewVehicleType = itemView.findViewById(R.id.textViewVehicleType);
        }
    }
}
