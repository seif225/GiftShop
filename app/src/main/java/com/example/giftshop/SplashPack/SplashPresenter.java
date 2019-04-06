package com.example.giftshop.SplashPack;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.giftshop.GoogleLoginPack.GoogleLoginActivity;
import com.example.giftshop.MailLoginPack.MailLoginActivity;
import com.example.giftshop.MainActivity;
import com.example.giftshop.PhoneLoginPack.PhoneLoginActivity;
import com.example.giftshop.SignUpPack.SignUpActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class SplashPresenter implements  ISplash {

   static Context context;
  static   FirebaseAuth mAuth;
    public SplashPresenter(Context context){
        mAuth=FirebaseAuth.getInstance();
        this.context=context;
    }


    public void loginWithGoogleAccount(int requestCode,int RC_SIGN_IN,Intent data){
        Log.e("errrrrrror","requestCode"+requestCode+"RC_SIGN_IN " + RC_SIGN_IN);
        if (requestCode == RC_SIGN_IN && data != null) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {

                    firebaseAuthWithGoogle(account);
                }

            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.e("Splash", "Google sign in failed", e);
                // ...
            }
        }
    }

    private static void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d("Splash Presenter", "firebaseAuthWithGoogle:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("Splash Presenter", "signInWithCredential:success");
                            enterToHome();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("Splash Presenter", "signInWithCredential:failure", task.getException());
                        }

                        // ...
                    }
                });
    }

    public static void enterToHome(){
        Intent i=new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }
    @Override
    public void sendUserToPhoneLoginActivity() {

        Intent intent = new Intent(context, PhoneLoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    public void sendUserToMailLogin() {
        Intent intent = new Intent(context, MailLoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        context.startActivity(intent);

    }



    @Override
    public void sendUserToSignUpActivity() {

        Intent intent = new Intent(context, SignUpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);


    }


}
