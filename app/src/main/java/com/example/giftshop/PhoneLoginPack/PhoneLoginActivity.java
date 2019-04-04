package com.example.giftshop.PhoneLoginPack;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.giftshop.R;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class PhoneLoginActivity extends AppCompatActivity {
 private    EditText phoneNum;
    private CountryCodePicker code;
    private static Context context;
   private Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);
        context=getApplicationContext();
        final PhoneLoginPresenter phoneLoginPresenter=PhoneLoginPresenter.getInstance(getContext());

        initialization();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pNumber="+"+code.getFullNumber();
                pNumber=pNumber+phoneNum.getText();
                Log.e("ssssssssss",pNumber);
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        pNumber,
                        60,
                        TimeUnit.SECONDS,
                        PhoneLoginActivity.this,
                        phoneLoginPresenter.mCallbacks
                );
            }
        });

    }
    private Context getContext(){
        return context;
    }
    private void initialization() {
        phoneNum=findViewById(R.id.phone_number);
        code=findViewById(R.id.countryCode);
        signUp=findViewById(R.id.sign_up_with_phone);
    }
}
