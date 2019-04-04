package com.example.giftshop.SignUpPack;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.giftshop.SplashPack.SplashActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpPresenter implements ISignUp {
    private Context context;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference userRef;
    public  SignUpPresenter(Context context){

        this.context=context;
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        userRef=firebaseDatabase.getReference().child("users");
    }


    @Override
    public void CreateNewUser(final String mail, final String password, final ProgressDialog progressDialog) {
        progressDialog.setMessage("Regisitering your account");
        progressDialog.setCancelable(false);
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    addUserToFireBaseDatabase(mail,password,progressDialog);

                }

                else{

                    Toast.makeText(context, task.getException()+"", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }



            }
        });
    }

    @Override
    public void addUserToFireBaseDatabase(String mail, String password, final ProgressDialog progressDialog) {

        userRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("mail").setValue(mail);
        userRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("password").setValue(password).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(context, "your account has been created", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, SplashActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });


    }
}
