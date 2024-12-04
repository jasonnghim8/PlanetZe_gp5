package com.example.planetZe_gp5.acf;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.planetZe_gp5.MainActivity;
import com.example.planetZe_gp5.R;

public class RecalACF extends MainActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button cancel = findViewById(R.id.cancel_recal);
        Button cont = findViewById(R.id.confirm_recal);

        cancel.setOnClickListener(v -> {
            Intent intent = new Intent(RecalACF.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            startActivity(intent);
        });
        cont.setOnClickListener(v -> {
            Intent intent = new Intent(RecalACF.this, CountrySelection.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            startActivity(intent);
        });
    }
}
