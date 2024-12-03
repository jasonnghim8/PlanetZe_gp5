package com.example.planetZe_gp5.acf;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.planetZe_gp5.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class ACFResult2 extends AppCompatActivity {
    private TextView tvTransport, tvFood, tvHouse, tvConsume;
    private Button cont;
    private Button back;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acfresult2);

        tvTransport = findViewById(R.id.tvTransport);
        tvFood = findViewById(R.id.tvFood);
        tvHouse = findViewById(R.id.tvHouse);
        tvConsume = findViewById(R.id.tvConsume);
        pieChart = findViewById(R.id.piechart);

        Calculation cal = Calculation.getInstance();

        tvTransport.setText(String.valueOf(100*cal.transportCF/cal.totalCF));
        tvFood.setText(String.valueOf(100*cal.foodCF/cal.totalCF));
        tvHouse.setText(String.valueOf(100*cal.housingCF/cal.totalCF));
        tvConsume.setText(String.valueOf(100*cal.consumptionCF/cal.totalCF));

       int color = ContextCompat.getColor(this, R.color.blue);
       pieChart.addPieSlice(new PieModel("transportation",
               (float) (cal.transportCF/cal.totalCF),
               color));
       color = ContextCompat.getColor(this, R.color.dark_blue);
       pieChart.addPieSlice(new PieModel("Food", (float)(cal.foodCF/cal.totalCF),
               color));

       color = ContextCompat.getColor(this, R.color.dark);
       pieChart.addPieSlice(new PieModel("Housing", (float)(cal.housingCF/cal.totalCF),
               color));

       color = ContextCompat.getColor(this, R.color.grey);
       pieChart.addPieSlice(new PieModel("Consumption",
               (float)(cal.consumptionCF/cal.totalCF),
               color));


        cont = findViewById(R.id.acf2cont);
        cont.setOnClickListener(v -> {
            Intent intent = new Intent(ACFResult2.this, ACFResults3.class);
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