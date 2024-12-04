package com.example.planetZe_gp5.login_registration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.planetZe_gp5.LocalData;
import com.example.planetZe_gp5.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginModel {
    private FirebaseAuth auth;
    private Context loginContext;
    public LoginModel(FirebaseAuth auth, Context loginContext){
        this.auth = auth;
        this.loginContext = loginContext;
    }
    public void loginUsingEmailPassword(String email, String pass){
        auth.signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        LocalData.setUserid(email.substring(0, email.indexOf("@")));
                        Toast.makeText(loginContext, "Login Successful!", Toast.LENGTH_SHORT).show();
                        loginContext.startActivity(new Intent(loginContext, MainActivity.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(loginContext, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void resetPassword(String email){
        auth.sendPasswordResetEmail(email)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(loginContext, "Reset Password link has been sent to your registered Email", Toast.LENGTH_SHORT).show();
                        loginContext.startActivity(new Intent(loginContext, LoginActivity.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(loginContext, "Error :- " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
