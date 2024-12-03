package com.example.planetZe_gp5.acf;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.planetZe_gp5.R;


public class Welcome extends AppCompatActivity {
    private TextView welcome;
    private TextView explain;

    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcome = findViewById(R.id.welcome);
        explain = findViewById(R.id.message);
        start = findViewById(R.id.start);

        welcome.setText(R.string.welcome);
        explain.setText(R.string.welcomeMessage);

        start.setOnClickListener(v -> {
            Intent intent1 = new Intent(Welcome.this, CountrySelection.class);
            startActivity(intent1);
        });
    }
}
