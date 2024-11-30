package com.example.planetZe_gp5.ecotracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planetZe_gp5.DataModel;
import com.example.planetZe_gp5.LocalData;
import com.example.planetZe_gp5.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class EcoTrackerMainFragment extends Fragment {
    ImageButton buttonAdd;
    ImageButton buttonDelete;
    Button buttonSave;
    CalendarView calendarView;
    DataModel dbModel;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<Item> itemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ecotracker_main, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        buttonAdd = view.findViewById(R.id.buttonAdd);
        buttonDelete = view.findViewById(R.id.buttonDelete);
        buttonSave = view.findViewById(R.id.buttonSave);
        calendarView = view.findViewById(R.id.calendarView);

        itemList = new ArrayList<>();
        itemAdapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(itemAdapter);

        // set calendar date to previously selected day or set default to the current day
        long dateTime =  LocalData.calendarDate != 0 ? LocalData.calendarDate : calendarView.getDate();
        calendarView.setDate(dateTime);
        String date = new SimpleDateFormat("yyyy-M-d", Locale.getDefault()).format(new Date(dateTime));

        dbModel = DataModel.getInstance();
        dbModel.ecoTrackerPath = "categories/" + date;
        dbModel.listTrackerValues(itemList, itemAdapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new EcoTrackerAddItemFragment());
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelected();
            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.clearFocus(); // clearing focus updates item value after edit
                saveChanged();
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day) {
                dbModel.ecoTrackerPath = "categories/" + year + "-" + (month + 1) + "-" + day;
                dbModel.listTrackerValues(itemList, itemAdapter);
                LocalData.calendarDate = new GregorianCalendar(year, month, day).getTimeInMillis();
            }
        });

        return view;
    }

    private void deleteSelected() {
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);

            // remove selected items from database and list then update adapter view
            if (item.selected) {
                dbModel.deleteEntry(dbModel.ecoTrackerPath, item.key);
                itemList.remove(i);
                itemAdapter.notifyItemRemoved(i);
                itemAdapter.notifyItemRangeChanged(i, itemList.size() - i);
                i--;  // decrement i because an item is removed
            }
        }
    }

    private void saveChanged() {
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);

            // reset selected items and update database for changed items
            if (item.selected) {
                if (item.changed) {
                    dbModel.writeEcoTrackerData(item.key, item.value);
                    item.changed = false;
                }
                itemAdapter.notifyItemChanged(i);
            }
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}