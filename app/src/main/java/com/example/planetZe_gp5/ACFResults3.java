package com.example.planetZe_gp5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ACFResults3 extends AppCompatActivity {
    private TextView result, region, global;
    private String userid;
    private DataModel dbModel;
    private Button cont;
    private Button back;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        dbModel = new DataModel();
        setContentView(R.layout.activity_acfresult3);

        result = findViewById(R.id.acfresult3);
        region = findViewById(R.id.comparison);
        global = findViewById(R.id.globalTargets);
        cont = findViewById(R.id.continue3);

        Intent lastPage = getIntent();
        userid = lastPage.getStringExtra("userid");
        if (userid == null) userid = "test";

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
        String compare = "Your country/region average is " + countryCf;
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

        compare = compare + "\n" + compare2;
        region.setText(compare);

        String compareGb = "The global average is 2 tonnes";
        double diffGb = 2 - cf;
        double diffPercentGb = Math.abs(diffGb)/countryCf;

        String compare3 = "Your carbon footprint is " + diffPercentGb + "% ";
        if (diffGb == 0){
            compare3 = compare3 + "equal to";
        }
        else if (diffGb < 0) {
            compare3 = compare3 + "below";
        }
        else{
            compare3 = compare3 + "above";
        }
        compare3 = compare3 + " the global average";

        region.setText(compare3);

        cont = findViewById(R.id.continue3);
        cont.setOnClickListener(v -> {
            Intent intent = new Intent(ACFResults3.this, MainActivity.class);
            intent.putExtra("userid", userid);
            intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            startActivity(intent);
        });

        back = findViewById(R.id.acf3back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ACFResults3.this, ACFResult2.class);
                intent.putExtra("userid", userid);
                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                startActivity(intent);
            }
        });
    }
}
