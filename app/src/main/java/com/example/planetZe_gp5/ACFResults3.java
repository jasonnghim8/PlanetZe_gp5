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

public class ACFResults3 extends AppCompatActivity implements Observer{
    private TextView result, region, global;
    private String userid;
    private DataModel dbModel;
    private Button cont;
    private Button back;
    private int readCount; // number of times data has finished reading from database
    private String userArea;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        dbModel = new DataModel();
        setContentView(R.layout.activity_acfresult3);

        result = findViewById(R.id.acfresult3);
        region = findViewById(R.id.comparison);
        global = findViewById(R.id.globalTargets);
        cont = findViewById(R.id.continue3);
        back = findViewById(R.id.acf3back);

        Intent lastPage = getIntent();
        userid = lastPage.getStringExtra("userid");
        if (userid == null) userid = "test";

        String path = "Users/" + userid + "/location";
        dbModel.readValue(path, this);

        cont.setOnClickListener(v -> {
            Intent intent = new Intent(ACFResults3.this, CountrySelection.class);
            intent.putExtra("userid", userid);
            intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            startActivity(intent);
        });

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

    private String compareString(double difference) {
        if (Math.abs(difference) < 0.00001) {
            return "equal to";
        }
        if (difference < 0) {
            return "below";
        }
        return "above";
    }

    @Override
    public void updateAfterRead(Object valueRead) {
        // first read gets userArea, next read gets emission for that area.
        readCount++;
        if (readCount == 1) {
            userArea = (String) valueRead;
            String path = "CountryAnnualPerCapita";
            dbModel.searchCorrData(path, this, "Country", userArea, "emission");
            return;
        }

        // update page now that we have both values
        double countryCf = (double) valueRead;

        double cf = 0;
        String cfResult = "Your current carbon footprint is" + cf;
        result.setText(cfResult);

        String compare = "Your country/region average is " + countryCf;
        double diff = countryCf - cf;
        double diffPercent = Math.abs(diff)/countryCf;

        String compare2 = "Your carbon footprint is " + diffPercent + "% " + compareString(diff);
        compare2 = compare2 + " the national average for" + userArea;

        compare = compare + "\n" + compare2;
        region.setText(compare);

        String compareGb = "The global average is 2 tonnes";
        double diffGb = 2 - cf;
        double diffPercentGb = Math.abs(diffGb)/countryCf;

        String compare3 = "Your carbon footprint is " + diffPercentGb + "% " + compareString(diffGb) + " the global average";
        region.setText(compare3);
    }
}
