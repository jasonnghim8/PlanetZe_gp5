package com.example.planetZe_gp5;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class SpinnerFragment_Country extends Fragment {
    private TextView message;
    private Spinner spinner;
    private Button button;

    private DataModel dbModel;

    String userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_country_selection, container, false);

        spinner = view.findViewById(R.id.country_selection);
        message = view.findViewById(R.id.choose_country);
        button = view.findViewById(R.id.country_confirm);

        dbModel = new DataModel();

        message.setText(R.string.country_select);

        ArrayList<String> countries = getCountriesList();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String selected = spinner.getSelectedItem().toString();
                storeCountry(selected, userId);
                Intent intent = new Intent(getActivity(), ACFResults.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private ArrayList<String> getCountriesList(){
        ArrayList<String> countries = new ArrayList<>();

        for (Locale locale : Locale.getAvailableLocales()){
            String country = locale.getDisplayCountry();
            // Avoid duplicates and empty names
            if (!country.isEmpty() && !countries.contains(country)){
                countries.add(country);
            }
        }

        String[] sortedCountries = countries.toArray(new String[0]);
        Arrays.sort(sortedCountries);

        return new ArrayList<>(Arrays.asList(sortedCountries));
    }

    private void storeCountry(String country, String user){
        String parent = "Users/";
        dbModel.writeData(parent+userId+"/location", country);
    }
}