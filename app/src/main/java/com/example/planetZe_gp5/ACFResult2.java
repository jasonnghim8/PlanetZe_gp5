package com.example.planetZe_gp5;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.HashMap;

public class ACFResult2 extends AppCompatActivity {
    private TextView tvTransport, tvFood, tvHouse, tvConsume;
    private Button cont;
    private Button back;
    private PieChart pieChart;

    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acfresult2);

        tvTransport = findViewById(R.id.tvTransport);
        tvFood = findViewById(R.id.tvFood);
        tvHouse = findViewById(R.id.tvHouse);
        tvConsume = findViewById(R.id.tvConsume);
        pieChart = findViewById(R.id.piechart);

        Intent lastPage = getIntent();
        userId = lastPage.getStringExtra("userid");
        if (userId == null) userId = "test";
        calculation cal = new calculation(userId);

        tvTransport.setText(String.valueOf(cal.calculatePercentage("Transport")));
        tvFood.setText(String.valueOf(cal.calculatePercentage("Food")));
        tvHouse.setText(String.valueOf(cal.calculatePercentage("Housing")));
        tvConsume.setText(String.valueOf(cal.calculatePercentage("Consumption")));

        pieChart.addPieSlice(new PieModel(R.string.transport,
                Integer.parseInt(tvTransport.getText().toString())));
        Color.parseColor(String.valueOf(R.color.blue));

        pieChart.addPieSlice(new PieModel(R.string.food,
                Integer.parseInt(tvFood.getText().toString())));
        Color.parseColor(String.valueOf(R.color.dark_blue));

        pieChart.addPieSlice(new PieModel(R.string.housing,
                Integer.parseInt(tvHouse.getText().toString())));
        Color.parseColor(String.valueOf(R.color.dark));

        pieChart.addPieSlice(new PieModel(R.string.consumption,
                Integer.parseInt(tvConsume.getText().toString())));
        Color.parseColor(String.valueOf(R.color.grey));

        cont = findViewById(R.id.acf2cont);
        cont.setOnClickListener(v -> {
            Intent intent = new Intent(ACFResult2.this, ACFResults3.class);
            intent.putExtra("userid", userId);
            intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            startActivity(intent);
        });

        back = findViewById(R.id.acf2back);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(ACFResult2.this, ACFResults.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

}