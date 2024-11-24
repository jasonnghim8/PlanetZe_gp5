package com.example.planetZe_gp5;

import static com.example.planetZe_gp5.calculation.calculatePercentage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class acfResult2 extends AppCompatActivity {
    private TextView tvTransport, tvFood, tvHouse, tvConsume;
    private Button cont;
    private PieChart pieChart;

    private DataModel dbModel;
    double acf;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        dbModel = new DataModel();
        HashMap<String, Double> sum;
        setContentView(R.layout.activity_acfresult2);

        tvTransport = findViewById(R.id.tvTransport);
        tvFood = findViewById(R.id.tvFood);
        tvHouse = findViewById(R.id.tvHouse);
        tvConsume = findViewById(R.id.tvConsume);
        pieChart = findViewById(R.id.piechart);

        tvTransport.setText(calculatePercentage(sum, "Transport"));
        tvFood.setText(calculatePercentage(sum, "Food"));
        tvHouse.setText(calculatePercentage(sum, "Transport"));
        tvConsume.setText(calculatePercentage(sum, "Transport"));

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
            Intent intent = new Intent(acfResult2.this, acfResults.class);
            startActivity(intent);
        });
    }}