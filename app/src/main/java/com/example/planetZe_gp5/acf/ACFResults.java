package com.example.planetZe_gp5.acf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.planetZe_gp5.DataModel;
import com.example.planetZe_gp5.Observer;
import com.example.planetZe_gp5.R;

public class ACFResults extends AppCompatActivity implements Observer {
    private TextView totalAcf;
    private Button cont;

    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        DataModel dbModel = DataModel.getInstance();

        Intent lastPage = getIntent();
        userId = lastPage.getStringExtra("userid");
        if (userId == null) userId = "test";

        setContentView(R.layout.activity_acftotal);
        totalAcf = this.findViewById(R.id.totalAcf);
        cont = findViewById(R.id.acfCont);

        // have to use read value on change method because the calculation takes time to write to database.
        dbModel.readValueOnChange("Users/"+userId + "/annualCarbonFootprint/total", this);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ACFResults.this, ACFResult2.class);
                intent.putExtra("userid", userId);
                startActivity(intent);
            }
        });

    }

    public void updateAfterRead(Object valueRead) {
        String value = (String) valueRead;
        String line = getResources().getString(R.string.acfResult) + value + " tonnes";
        totalAcf.setText(line);
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

}
