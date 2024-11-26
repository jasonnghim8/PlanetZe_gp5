package com.example.planetZe_gp5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ACFResults extends AppCompatActivity {
    private TextView totalAcf;
    private Button cont;

    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        DataModel dbModel = new DataModel();
        List<String> acf = new ArrayList<String>();
        Intent lastPage = getIntent();
        userId = lastPage.getStringExtra("userid");
        dbModel.readValue("Users/"+userId+"/annualCarbonFootprint",
                acf);
        setContentView(R.layout.activity_acftotal);

        totalAcf = this.findViewById(R.id.totalAcf);
        String line = R.string.acfResult + acf.get(0);
        totalAcf.setText(line);

        cont = findViewById(R.id.acfCont);
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ACFResults.this, ACFResult2.class);
                intent.putExtra("userid", userId);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

}