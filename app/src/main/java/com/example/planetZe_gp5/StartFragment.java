package com.example.planetZe_gp5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class StartFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_start_fragment, container, false);

        Button buttonHomeView = view.findViewById(R.id.buttonHomeView);
        Button buttonRead = view.findViewById(R.id.buttonRead);
        Button buttonShow = view.findViewById(R.id.buttonShow);
        Button ecotracker = view.findViewById(R.id.ecotracker);

        DataModel dbModel = new DataModel();
        ArrayList<String> strList1 = new ArrayList<String>();
        ArrayList<String> strList2 = new ArrayList<String>();

        buttonHomeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new HomeFragment());
            }
        });

        // reading values example
        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbModel.readValue("CountryAnnualPerCapita/0/Country", strList1);
                dbModel.readData("exampleRead", strList2);
            }
        });
        // showing contents of strList1 and strList2
        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (strList1.size() >= 1) {
                    Toast.makeText(getContext(), strList1.get(0), Toast.LENGTH_SHORT).show();
                }
                for(int i = 0; i < strList2.size(); i++) {
                    Toast.makeText(getContext(), strList2.get(i), Toast.LENGTH_SHORT).show();
                }
            }
        });
        ecotracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new AddItemEcoTrackerFragment());
            }
        });

        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

