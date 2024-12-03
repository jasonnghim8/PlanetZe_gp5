package com.example.planetZe_gp5;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import android.widget.EditText;

import com.example.planetZe_gp5.login_registration.LoginModel;
import com.example.planetZe_gp5.login_registration.LoginPresenter;

public class LoginUnitTests {

    @Test
    public void loginEmptyEmail(){
        EditText loginEmail = mock(EditText.class);
        LoginPresenter presenter = new LoginPresenter(loginEmail, null, null, null, null);
        String email = "";
        String pass = "abc";
        assertFalse(presenter.loginWithEmailAndPassword(email, pass));
    }

    @Test
    public void loginEmptyPass(){
        EditText loginEmail = mock(EditText.class);
        LoginPresenter presenter = new LoginPresenter(loginEmail, loginEmail, null, null, null);
        String email = "abc@gmail.com";
        String pass = "";
        assertFalse(presenter.loginWithEmailAndPassword(email, pass));
    }

    @Test
    public void loginInvalidEmail(){
        EditText loginEmail = mock(EditText.class);
        LoginPresenter presenter = new LoginPresenter(loginEmail, loginEmail, null, null, null);
        String email = "abc";
        String pass = "ach";
        assertFalse(presenter.loginWithEmailAndPassword(email, pass));
    }

    @Test
    public void loginHappyCase(){
        LoginModel model = mock(LoginModel.class);
        String email = "abc@gmail.com";
        String pass = "ach";
        LoginPresenter presenter = new LoginPresenter(null, null, null, null, model);
        assertTrue(presenter.loginWithEmailAndPassword(email, pass));
    }

    @Test
    public void resetPassEmptyEmail(){
        EditText loginEmail = mock(EditText.class);
        LoginPresenter presenter = new LoginPresenter(loginEmail, null, null, null, null);
        String email = "";
        assertFalse(presenter.resetPassword(email));
    }

    @Test
    public void resetPassInvalidEmail(){
        EditText loginEmail = mock(EditText.class);
        LoginPresenter presenter = new LoginPresenter(loginEmail, null, null, null, null);
        String email = "acb";
        assertFalse(presenter.resetPassword(email));
    }

    @Test
    public void resetPassHappyCase(){
        EditText loginEmail = mock(EditText.class);
        LoginModel model = mock(LoginModel.class);
        LoginPresenter presenter = new LoginPresenter(loginEmail, null, null, null, model);
        String email = "abc@gmail.com";
        assertTrue(presenter.resetPassword(email));
    }
}