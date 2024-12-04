package com.example.planetZe_gp5.ecotracker;

import static com.example.planetZe_gp5.LocalData.CONSUMPTION;
import static com.example.planetZe_gp5.LocalData.FOOD;
import static com.example.planetZe_gp5.LocalData.TRANSPORTATION;
import static com.example.planetZe_gp5.LocalData.allHabitsList;
import static com.example.planetZe_gp5.LocalData.habitList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planetZe_gp5.DataModel;
import com.example.planetZe_gp5.LocalData;
import com.example.planetZe_gp5.Observer;
import com.example.planetZe_gp5.R;
import com.google.android.material.chip.Chip;

import java.util.Collections;
import java.util.HashMap;

public class HabitTrackerFragment extends Fragment implements Observer {
    private RecyclerView recyclerView;
    private HabitAdapter habitAdapter;
    private HashMap<String, AverageFootprint> idToFootprint;
    private Chip impactChip, transportationChip, foodChip, consumptionChip;
    private final boolean[] includeCategory = {false, false, false};
    private Button buttonLog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habit_tracker, container, false);
        impactChip = view.findViewById(R.id.chipImpact);
        transportationChip = view.findViewById(R.id.chipTransportation);
        foodChip = view.findViewById(R.id.chipFood);
        consumptionChip = view.findViewById(R.id.chipConsumption);
        recyclerView = view.findViewById(R.id.recyclerView);
        buttonLog = view.findViewById(R.id.buttonLog);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DataModel dbModel = DataModel.getInstance();

        LocalData.initializeHabitList();

        idToFootprint = new HashMap<>();
        dbModel.readAllItemFootprints(idToFootprint, this);

        impactChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sortByImpact(true);
            }
        });
        transportationChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                includeCategory[TRANSPORTATION] = isChecked;
                filterByCategory();
            }
        });
        foodChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                includeCategory[FOOD] = isChecked;
                filterByCategory();
            }
        });
        consumptionChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                includeCategory[CONSUMPTION] = isChecked;
                filterByCategory();
            }
        });

        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Habit habit : habitList) {
                    if (habit.selected) {
                        habit.logCount++;  // Increment the log count for selected habits
                    }
                }
                habitAdapter.notifyDataSetChanged();  // Refresh the RecyclerView to display the updated counts
            }
        });

        return view;
    }

    public void updateAfterRead(Object map) {
        updateOffsetValues();
        habitAdapter = new HabitAdapter(habitList);
        recyclerView.setAdapter(habitAdapter);
    }

    private void updateOffsetValues() {
        // reset offset to calculate updated average impact
        for (Habit habit : habitList) {
            habit.offsetValue = habit.carbonFootprint;
            for (String replacedHabit : habit.replacedHabits) {
                if (idToFootprint.containsKey(replacedHabit)) {
                    AverageFootprint footprint = idToFootprint.get(replacedHabit);
                    habit.offsetValue -= footprint.footprint / footprint.count;
                }
            }
        }
    }

    private void sortByImpact(boolean notifyDataSet) {
        if (!impactChip.isChecked()) return;

        Collections.sort(habitList);
        if (notifyDataSet) {
            habitAdapter.notifyDataSetChanged();
        }
    }

    private void filterByCategory() {
        habitList.clear();
        // if all filters are unchecked then show all habits.
        if (!includeCategory[TRANSPORTATION] && !includeCategory[FOOD] && !includeCategory[CONSUMPTION]) {
            habitList.addAll(allHabitsList);
        }
        else {
            for (Habit habit : allHabitsList) {
                if (includeCategory[habit.category]) {
                    habitList.add(habit);
                }
            }
        }

        sortByImpact(false);
        habitAdapter.notifyDataSetChanged();
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
