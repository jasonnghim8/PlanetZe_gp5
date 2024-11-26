package com.example.planetZe_gp5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class CountrySelection extends AppCompatActivity {
    private TextView message;
    private Spinner spinner;
    private Button button;

    private DataModel dbModel;

    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        setContentView(R.layout.activity_country_selection);

        spinner = findViewById(R.id.country_selection);
        message = findViewById(R.id.choose_country);
        button = findViewById(R.id.country_confirm);

        dbModel = new DataModel();

        message.setText(R.string.country_select);

        ArrayList<String> countries = getCountriesList();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        button.setOnClickListener(v -> {
            String selected = spinner.getSelectedItem().toString();
            storeCountry(selected);
            Intent intent1 = new Intent(CountrySelection.this, ACFQuestion.class);
            intent1.putExtra("userid", userid);
            startActivity(intent1);
        });
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

    private void storeCountry(String country){
        String parent = "Users/";
        dbModel.writeData(parent+userid+"/location", country);
    }
}
