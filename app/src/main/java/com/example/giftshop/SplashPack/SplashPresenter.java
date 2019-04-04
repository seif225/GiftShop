package com.example.giftshop.SplashPack;

import android.content.Context;
import android.content.Intent;

import com.example.giftshop.GoogleLoginPack.GoogleLoginActivity;
import com.example.giftshop.MailLoginPack.MailLoginActivity;
import com.example.giftshop.PhoneLoginPack.PhoneLoginActivity;
import com.example.giftshop.SignUpPack.SignUpActivity;

public class SplashPresenter implements  ISplash {

    Context context;
    public SplashPresenter(Context context){

    this.context=context;


    }


    @Override
    public void sendUserToPhoneLoginActivity() {

        Intent intent = new Intent(context, PhoneLoginActivity.class);

        context.startActivity(intent);
    }

    @Override
    public void sendUserToMailLogin() {
        Intent intent = new Intent(context, MailLoginActivity.class);

        context.startActivity(intent);

    }

    @Override
    public void sendUserToGoogleLogin() {
        Intent intent = new Intent(context, GoogleLoginActivity.class);

        context.startActivity(intent);

    }

    @Override
    public void sendUserToSignUpActivity() {

        Intent intent = new Intent(context, SignUpActivity.class);

        context.startActivity(intent);


    }


}
