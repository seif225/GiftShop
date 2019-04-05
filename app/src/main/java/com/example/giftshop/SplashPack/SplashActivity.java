package com.example.giftshop.SplashPack;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.giftshop.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class SplashActivity extends AppCompatActivity {


    private static final int RC_SIGN_IN =0 ;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;
    private static Context context;
    SplashPresenter splashPresenter;
    Button googleButton,mailButton,phoneButton,signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        intializeFields();
        context=getBaseContext();
        splashPresenter=new SplashPresenter(getContext());

        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogleAccount();
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
    private static Context getContext() {
        return context;
    }

    private void intializeFields() {

    splashPresenter=new SplashPresenter(this);
    googleButton=findViewById(R.id.splash_google_login);
    mailButton=findViewById(R.id.splash_mail_login);
    phoneButton=findViewById(R.id.splash_phone_login);
    signUpButton=findViewById(R.id.splash_sign_up);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("662167942752-tkeollsbrvtj53nthpfmsir84599g2r2.apps.googleusercontent.com")
                .requestEmail()
                .build();
    }
    private void signInWithGoogleAccount(){
        mGoogleSignInClient = GoogleSignIn.getClient(SplashActivity.this, gso);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        splashPresenter.loginWithGoogleAccount(requestCode,RC_SIGN_IN,data);
    }
}
