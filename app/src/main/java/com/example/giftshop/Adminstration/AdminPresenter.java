package com.example.giftshop.Adminstration;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AdminPresenter  {


    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private Context context;
   private StorageReference storageReference;
    private String url,category;
    private Map<Object , Object> objectMap=new HashMap<>();


   public AdminPresenter(Context context){
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference().child("gifts");
        this.context=context;
        storageReference=FirebaseStorage.getInstance().getReference().child("places_images");
        mAuth=FirebaseAuth.getInstance();
    }


    void uploadPicture(Uri iamgeUri, final ProgressDialog progressDialog){
           progressDialog.setMessage("uploading ..");
           progressDialog.setCancelable(false);
           progressDialog.show();
        final String imageName = UUID.randomUUID().toString() + ".jpg";
        storageReference.child(imageName).putFile(iamgeUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.child(imageName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        url=uri.toString();
                        if(!url.isEmpty()){
                            objectMap.put("Image",url);
                            uploadDataToFirebase(progressDialog);

                        }
                    }
                });
            }
        });
    }

    private void uploadDataToFirebase(final ProgressDialog progressDialog) {

       reference.child(category).push().setValue(objectMap).addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
           public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful()){

                   progressDialog.dismiss();
                   Toast.makeText(context, "Update data Complete", Toast.LENGTH_SHORT).show();
               }
           }
       });



   }

    public void pushing(String title, String price, String priceRange, String gender, String color
            , String category,String description) {
        Map<Object ,Object >s=new HashMap<>();

        s.put("title",title);
        s.put("price",price);
        s.put("price range",priceRange);
        s.put("gender",gender);
        s.put("color",color);
        s.put("category",category);
        s.put("description",description);
        objectMap.putAll(s);
       this.category=category;
        Log.e("Well done", "Wellllllllllll done");
        //cities.child(city).child(category).child(placeName).setValue(objectMap);
    }



}
