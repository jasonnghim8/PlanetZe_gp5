package com.example.planetZe_gp5.ecotracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planetZe_gp5.LocalData;
import com.example.planetZe_gp5.R;
import com.google.android.material.chip.Chip;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Item> itemList;

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_adapater, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.text.setText(LocalData.ETGetValue(item.key));
        holder.value.setText(item.value);
        holder.footprint.setText("" + Calculation.calculateFootprint(item.key, item.value));
        holder.value.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String val = holder.value.getText().toString();
                    // only update value if it is changed
                    if (!val.equals(item.value)) {
                        item.value = val;
                        item.changed = true;
                        holder.chipSelect.setChecked(true);
                    }
                }
                }
        });

        holder.chipSelect.setChecked(false);
        item.selected = false;

        holder.chipSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.selected = isChecked;
            }
        });

        EcoTrackerMainFragment.updateTotalEmission(itemList);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView text, value, footprint;
        Chip chipSelect;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.name);
            value = itemView.findViewById(R.id.editValue);
            footprint = itemView.findViewById(R.id.textFootprint);
            chipSelect = itemView.findViewById(R.id.chip);
        }
    }
}
