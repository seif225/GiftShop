package com.example.giftshop.PhoneLoginPack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.giftshop.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class PhoneLoginPresenter {

   private FirebaseAuth mAuth;
   private String id;
    static Context context;
    private static PhoneLoginPresenter instance=new PhoneLoginPresenter();
    static PhoneLoginPresenter getInstance(Context ctext){
        context=ctext;
        return instance;
    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            Log.e("PhoneLoginPresenter", "compeleteeeeeeeeee");
            signInWithPhoneAuthCredential(phoneAuthCredential);

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Log.e("failed",e.getMessage());
        }


        @Override
        public void onCodeSent(String verificationId,
                               PhoneAuthProvider.ForceResendingToken token) {

            Log.e("PhoneLoginPresenter", "onCodeSent:" + verificationId);
            id=verificationId;
            Intent i=new Intent(context,VerificationCode.class);
            context.startActivity(i);

            // ...
        }
    };
    private PhoneLoginPresenter(){

        mAuth=FirebaseAuth.getInstance();
        Log.e("constractor","sssssssss");
    }
     void createCredential(String code){
        Log.e("credential",id+"  "+code);
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(id, code);
        signInWithPhoneAuthCredential(credential);

    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential phoneAuthCredential) {
        mAuth.signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("PhoneLoginPresenter", "signInWithCredential:success");

                            goToMainActivity();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.e("PhoneLoginPresenter", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    private void goToMainActivity(){
        Intent i=new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(i);
    }

}
