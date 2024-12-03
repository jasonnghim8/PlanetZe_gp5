package com.example.planetZe_gp5.login_registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.planetZe_gp5.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText emailText;
    private Button sendEmailButton;
    private Button returnButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        auth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.email_text);
        sendEmailButton = findViewById(R.id.send_email_button);
        returnButton = findViewById(R.id.return_button);

        LoginModel model = new LoginModel(auth, ForgotPassword.this);
        LoginPresenter presenter = new LoginPresenter(emailText, null, auth, ForgotPassword.this, model);

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString().trim();
                presenter.resetPassword(email);
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPassword.this, LoginActivity.class));
            }
        });
    }
}