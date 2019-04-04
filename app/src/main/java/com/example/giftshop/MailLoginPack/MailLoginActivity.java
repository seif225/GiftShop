package com.example.giftshop.MailLoginPack;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.giftshop.R;

public class MailLoginActivity extends AppCompatActivity implements IMailLoginActivity{

    private EditText mailEt,passwordEt;
    private Button login;
    private String mail,password;
    private MailLoginPresenter mailLoginPresenter;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_login);
        intializeFields();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick();
            }
        });

    }

    @Override
    public void intializeFields() {
        mailLoginPresenter=new MailLoginPresenter(this);
        login=findViewById(R.id.login_login_button);
        mailEt=findViewById(R.id.login_mail_et);
        passwordEt=findViewById(R.id.login_password_et);
        progressDialog=new ProgressDialog(this);

    }

    @Override
    public void handleClick() {

        mail = mailEt.getText().toString();
        password=passwordEt.getText().toString();

        if(mail.isEmpty()){

            mailEt.setError("you cant leave mail empty ");

        }
        else if(password.isEmpty()){

            passwordEt.setError("you cant leave password empty");

        }

        else {

            mailLoginPresenter.LogUserIn(mail,password,progressDialog);

        }

    }
}
