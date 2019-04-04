package com.example.giftshop.PhoneLoginPack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.giftshop.R;
import com.hbb20.CountryCodePicker;

public class PhoneLoginActivity extends AppCompatActivity {
 private    EditText phoneNum;
    private CountryCodePicker code;
   private Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);

        initialization();


    }

    private void initialization() {
        phoneNum=findViewById(R.id.phone_number);
        code=findViewById(R.id.countryCode);
        signUp=findViewById(R.id.sign_up_with_phone);
    }
}
