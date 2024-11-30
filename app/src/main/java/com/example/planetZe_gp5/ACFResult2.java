package com.example.planetZe_gp5;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.eazegraph.lib.charts.PieChart;

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
        Calculation cal = Calculation.getInstance(userId);

        tvTransport.setText(String.valueOf(cal.transportCF/cal.totalCF));
        tvFood.setText(String.valueOf(cal.foodCF/cal.totalCF));
        tvHouse.setText(String.valueOf(cal.housingCF/cal.totalCF));
        tvConsume.setText(String.valueOf(cal.consumptionCF/cal.totalCF));

//        int color = Color.parseColor(String.valueOf(R.color.blue));
//        pieChart.addPieSlice(new PieModel("Transportation", (float)(cal.transportCF/cal.totalCF), color));
//
//        color = Color.parseColor(String.valueOf(R.color.dark_blue));
//        pieChart.addPieSlice(new PieModel("Food", (float)cal.foodCF, color));
//
//        color = Color.parseColor(String.valueOf(R.color.dark));
//        pieChart.addPieSlice(new PieModel("Housing", (float)cal.housingCF, color));
//
//        color = Color.parseColor(String.valueOf(R.color.grey));
//        pieChart.addPieSlice(new PieModel("Consumption", (float)cal.consumptionCF, color));


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