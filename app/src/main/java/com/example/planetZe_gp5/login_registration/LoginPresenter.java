package com.example.planetZe_gp5.login_registration;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.planetZe_gp5.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter {
    private EditText loginEmail;
    private EditText loginPassword;
    private FirebaseAuth auth;
    private Context loginContext;
    private LoginModel loginModel;
    public LoginPresenter(EditText loginEmail, EditText loginPassword, FirebaseAuth auth, Context loginContext, LoginModel loginModel){
        this.loginEmail = loginEmail;
        this.loginPassword = loginPassword;
        this.auth = auth;
        this.loginContext = loginContext;
        this.loginModel = loginModel;
    }
    public void loginWithEmailAndPassword(String email, String pass){
        if(email.isEmpty()){
            loginEmail.setError("Email cannot be empty");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            loginEmail.setError("Please enter valid email");
            return;
        }
        if(pass.isEmpty()){
            loginPassword.setError("Password cannot be empty");
            return;
        }
        loginModel.loginUsingEmailPassword(email, pass);
    }

    public void resetPassword(String email){
        if(email.isEmpty()){
            loginEmail.setError("Email cannot be empty");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            loginEmail.setError("Please enter valid email");
            return;
        }
        loginModel.resetPassword(email);
    }
}
