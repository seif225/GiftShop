package com.example.giftshop.MailLoginPack;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.giftshop.Adminstration.AdminActivity;
import com.example.giftshop.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MailLoginPresenter implements IMailLoginPresenter {

    private Context context;
    private FirebaseAuth firebaseAuth;

    public MailLoginPresenter(Context context){
        this.context=context;
        firebaseAuth=FirebaseAuth.getInstance();

    }


    @Override
    public void LogUserIn(String mail, String password, final ProgressDialog progressDialog) {
        progressDialog.setMessage("Signing in...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals("kAX91C9KjKWMser05rzIE6Jq3RJ3")){

                    sendUserToAdminActvitiy(progressDialog);


                    }

                    else {

                        sendUserToMainActivity(progressDialog);
                    }

                }
                else
                {
                    Toast.makeText(context, "Error:"+task.getException(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }


            }
        });






    }

    @Override
    public void sendUserToMainActivity(ProgressDialog progressDialog) {
        progressDialog.dismiss();
        Intent i = new Intent(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }

    @Override
    public void sendUserToAdminActvitiy(ProgressDialog progressDialog) {
    progressDialog.dismiss();
        Intent i = new Intent(context, AdminActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }
}
