package com.example.planetZe_gp5.login_registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.planetZe_gp5.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText fullName;
    private EditText signupEmail;
    private EditText signupPassword;
    private EditText confirmPassword;
    private Button signupButton;
    private TextView loginRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        fullName = findViewById(R.id.full_name);
        confirmPassword = findViewById(R.id.confirm_password);

        signupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String user = signupEmail.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();
                String name = fullName.getText().toString().trim();
                String confirmPass = confirmPassword.getText().toString().trim();

                if(name.isEmpty()){
                    fullName.setError("Full Name cannot be empty!");
                    return;
                }
                if (user.isEmpty()){
                    signupEmail.setError("Email cannot be empty!");
                    return;
                }
                if (pass.isEmpty()){
                    signupPassword.setError("Password cannot be empty!");
                    return;
                }
                if(!pass.equals(confirmPass)){
                    confirmPassword.setError("Passwords do not match!");
                    return;
                }

                auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "Signup Successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        }
                        else{
                            Toast.makeText(SignupActivity.this, "Signup Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });
    }
}