package com.example.giftshop.SignUpPack;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.giftshop.R;

public class SignUpActivity extends AppCompatActivity implements ISinUpActivity {
    private EditText mailEt,passwordEt;
    private Button registerButton;
    private String mail,password;
    private SignUpPresenter signUpPresenter;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        intializeFeilds();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = mailEt.getText().toString();
                password= passwordEt.getText().toString();

                if(mail.isEmpty()){

                    mailEt.setError("you must write your mail");

                }

                else if (password.isEmpty()){
                    passwordEt.setError("you should write a password");

                }
                else{

                signUpPresenter.CreateNewUser(mail,password,progressDialog);}

            }
        });




    }


    @Override
    public void intializeFeilds() {

        mailEt=findViewById(R.id.sign_up_mail_et);
        passwordEt=findViewById(R.id.sign_up_password_et);
        registerButton=findViewById(R.id.sign_up_register_button);
        signUpPresenter=new SignUpPresenter(this);
        progressDialog=new ProgressDialog(this);

    }
}
