package com.example.giftshop.SplashPack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.giftshop.R;

public class SplashActivity extends AppCompatActivity {



    SplashPresenter splashPresenter;
    Button googleButton,mailButton,phoneButton,signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        intializeFields();

        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splashPresenter.sendUserToGoogleLogin();
            }
        });

        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splashPresenter.sendUserToMailLogin();
            }
        });


        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splashPresenter.sendUserToPhoneLoginActivity();;


            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splashPresenter.sendUserToSignUpActivity();
            }
        });


    }

    private void intializeFields() {

    splashPresenter=new SplashPresenter(this);
    googleButton=findViewById(R.id.splash_google_login);
    mailButton=findViewById(R.id.splash_mail_login);
    phoneButton=findViewById(R.id.splash_phone_login);
    signUpButton=findViewById(R.id.splash_sign_up);
    }
}
