package com.example.giftshop.SignUpPack;

import android.app.ProgressDialog;

public interface ISignUp {


    void CreateNewUser(String mail, String password, ProgressDialog progressDialog);
    void addUserToFireBaseDatabase(String mail , String password ,ProgressDialog progressDialog);


}
