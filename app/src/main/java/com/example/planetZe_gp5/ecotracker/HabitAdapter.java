package com.example.planetZe_gp5.ecotracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planetZe_gp5.R;

import java.text.BreakIterator;
import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.ItemViewHolder> {
    private List<Habit> habitList;

    public HabitAdapter(List<Habit> habitList) {
        this.habitList = habitList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_habit_adapater, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Habit habit = habitList.get(position);

        holder.textName.setText(habit.name);
        holder.textDescription.setText(habit.description);
        double roundedOffset = Math.round(habit.offsetValue * 100) / 100.0;
        holder.textOffset.setText(roundedOffset + " CO2");
        holder.textLog.setText(String.valueOf(habit.logCount));

        holder.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                habit.selected = isChecked;  // Update the selected state
            }
        });
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textDescription, textOffset, textLog;
        Switch switchButton;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.name);
            textDescription = itemView.findViewById(R.id.description);
            textOffset = itemView.findViewById(R.id.footprintOffset);
            switchButton = itemView.findViewById(R.id.switch1);
            textLog = itemView.findViewById(R.id.textLog);
        }
    }
}
