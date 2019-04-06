package com.example.giftshop.Adminstration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.giftshop.R;
import com.example.giftshop.SplashPack.SplashActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity implements IAdminActivity{
     private String title,price,priceRangeString,gender,color,categoryString,description;
     private Spinner ganderS,colorS,priceRangeS,categoryS;
     private EditText priceEt ,titleEt,descriptionEt;
     private Button uploadPicture,uploadData,logOut;
    private static final int PICK_IMG_REQUEST =1 ;
    private Uri imageUri;
    AdminPresenter adminPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
         intializeFields();
         callSpinnerArrays();

         logOut.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 FirebaseAuth.getInstance().signOut();
                 Intent i = new Intent(AdminActivity.this, SplashActivity.class);
                 i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                 startActivity(i);
             }
         });

         uploadData.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                 callSpiners();
                 title=titleEt.getText().toString();
                 price=priceEt.getText().toString();
                 description=descriptionEt.getText().toString();

                 if(title.isEmpty() ||price.isEmpty() ||priceRangeString.isEmpty() ||color.isEmpty() ||description.isEmpty()
                         ||categoryString.isEmpty() ||gender.isEmpty() ){

                     Toast.makeText(AdminActivity.this, "fill all", Toast.LENGTH_SHORT).show();


                 }
                 else {

                     Log.e("here",title + price + priceRangeString+gender+color+categoryString+description+"");
                     adminPresenter.uploadPicture(imageUri,new ProgressDialog(AdminActivity.this));
                     adminPresenter.pushing(title,price,priceRangeString,gender,color,categoryString,description);


                 }
             }
         });

            uploadPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, PICK_IMG_REQUEST);
                }
            });

         //callSpiners();

        //gowa al on item click listener 7ot code dah
        //ganderS.getSelectedItem().toString();


    }

    @Override
    public void callSpiners() {

        priceRangeString = priceRangeS.getSelectedItem().toString();
        gender=ganderS.getSelectedItem().toString();
        color=colorS.getSelectedItem().toString();
        categoryString=categoryS.getSelectedItem().toString();

    }

    @Override
    public void intializeFields() {
        ganderS=findViewById(R.id.gender_spinner);
        colorS=findViewById(R.id.color_spinner);
        categoryS=findViewById(R.id.category_spinner);
        priceRangeS=findViewById(R.id._dynamic);
        priceEt=findViewById(R.id.price_et);
        descriptionEt=findViewById(R.id.description_et);
        uploadData=findViewById(R.id.admin_upload);
        uploadPicture=findViewById(R.id.upload_Admin_Picture);
        logOut=findViewById(R.id.log_out_admin);
        titleEt=findViewById(R.id.title_et);
        adminPresenter=new AdminPresenter(this);

    }

    @Override
    public void callSpinnerArrays() {
        final ArrayList<String>colorList=new ArrayList<>();
        colorList.add("white");
        colorList.add("red");
        colorList.add("yellow");
        colorList.add("blue");
        colorList.add("black");
        colorList.add("green");
        colorList.add("orange");
        colorList.add("aqua");
        colorList.add("indigo");
        colorList.add("violet");
        colorList.add("gray");
        colorList.add("beige");
        colorList.add("other");
        colorS.setAdapter(new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,colorList));

        //----------------------------------------------
        ArrayList<String>genderList=new ArrayList<>();
        genderList.add("male");
        genderList.add("female");

        ganderS.setAdapter(new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,genderList));

        final ArrayList<String>priceRange=new ArrayList<>();
        priceRange.add("50-100");
        priceRange.add("100-200");
        priceRange.add("200-500");
        priceRange.add("500-1000");
        priceRange.add("more than 1000");

        priceRangeS.setAdapter(new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,priceRange));

        final ArrayList<String>category=new ArrayList<>();
        category.add("perfumes");
        category.add("purses");
        category.add("birthday");
        category.add("valentine");
        category.add("wedding anniversary");
        category.add("other");

        categoryS.setAdapter(new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,category));


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && data != null)
            imageUri = data.getData();
        else
            Toast.makeText(this, "Please Pick Photo", Toast.LENGTH_LONG).show();

    }
}
