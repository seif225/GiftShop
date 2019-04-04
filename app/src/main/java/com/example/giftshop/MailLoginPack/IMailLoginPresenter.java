package com.example.giftshop.MailLoginPack;

import android.app.ProgressDialog;

public interface IMailLoginPresenter {

    void LogUserIn(String mail, String password, ProgressDialog progressDialog);
    void sendUserToMainActivity(ProgressDialog progressDialog);
    void sendUserToAdminActvitiy(ProgressDialog progressDialog);


}
