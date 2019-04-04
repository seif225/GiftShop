package com.example.giftshop.PhoneLoginPack;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.giftshop.R;

public class VerificationCode extends AppCompatActivity {

    EditText verifyCode;
    Button verifyButton;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);
        final PhoneLoginPresenter phoneLoginPresenter=PhoneLoginPresenter.getInstance(getContext());
        context=getApplicationContext();
        initialization();
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneLoginPresenter.createCredential(verifyCode.getText().toString());
            }
        });
    }
    private Context getContext(){
        return context;
    }

    private void initialization() {
        verifyButton=findViewById(R.id.verify_button);
        verifyCode=findViewById(R.id.verify_code);
    }
}
