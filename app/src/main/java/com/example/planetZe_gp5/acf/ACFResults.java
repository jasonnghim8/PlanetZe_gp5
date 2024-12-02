package com.example.planetZe_gp5.acf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.planetZe_gp5.DataModel;
import com.example.planetZe_gp5.LocalData;
import com.example.planetZe_gp5.Observer;
import com.example.planetZe_gp5.R;

public class ACFResults extends AppCompatActivity implements Observer {
    private TextView totalAcf;
    private Button cont;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        DataModel dbModel = DataModel.getInstance();

        setContentView(R.layout.activity_acftotal);
        totalAcf = this.findViewById(R.id.totalAcf);
        cont = findViewById(R.id.acfCont);

        // have to use read value on change method because the calculation takes time to write to database.
        dbModel.readUserValueOnChange("annualCarbonFootprint/total", this);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ACFResults.this, ACFResult2.class);
                startActivity(intent);
            }
        });

    }

    public void updateAfterRead(Object valueRead) {
        String value = (String) valueRead;
       // value = String.format("%.2f", Double.parseDouble(value));
        String line = getResources().getString(R.string.acfResult) +"\n" + value + " tonnes";
        totalAcf.setText(line);
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

}
