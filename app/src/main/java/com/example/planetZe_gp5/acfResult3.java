package com.example.planetZe_gp5;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class acfResult3 extends AppCompatActivity {
    private TextView result, region, global;
    private String userid;
    private DataModel dbModel;
    private Button cont;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        dbModel = new DataModel();
        setContentView(R.layout.activity_acfresult3);

        result = findViewById(R.id.acfresult3);
        region = findViewById(R.id.comparison);
        global = findViewById(R.id.globalTargets);
        cont = findViewById(R.id.continue3);

        double cf = 0;
        String cfResult = "Your current carbon footprint is" + cf;
        result.setText(cfResult);

        List<String> userArea = new ArrayList<>();
        String path = "/Users/";
        path = path + userid + "/location";
        dbModel.readData(path, userArea);

        List<Double> area = new ArrayList<>();
        path = "/CountryAnnualPerCapita";
        dbModel.searchCorrData(path, Collections.singletonList(area),
                "Country", userArea.get(0), "emission");
        double countryCf = area.get(0);
        String compare = "Your country average is " + countryCf;
        double diff = countryCf - cf;
        double diffPercent = Math.abs(diff)/countryCf;

        String compare2 = "Your carbon footprint is " + diffPercent + "% ";
        if (diff == 0){
            compare2 = compare2 + "equal to";
        }
        else if (diff < 0) {
            compare2 = compare2 + "below";
        }
        else{
            compare2 = compare2 + "above";
        }
        compare2 = compare2 + " the national average for" + userArea.get(0);

    }
}
