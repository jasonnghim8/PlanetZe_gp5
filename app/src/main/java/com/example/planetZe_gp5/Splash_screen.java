package com.example.planetZe_gp5;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.example.planetZe_gp5.login_registration.Welcome;

public class Splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Splash_screen.this, Welcome.class);
            startActivity(intent);
            finish();
        }, 2000);
    }
}
