package com.example.giftshop.Adminstration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.giftshop.R;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ArrayList<String>list=new ArrayList<>();
        list.add("male");
        list.add("female");
        Spinner ganderS=findViewById(R.id.gender_spinner);

        ganderS.setAdapter(new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,list));


        //gowa al on item click listener 7ot code dah
        //ganderS.getSelectedItem().toString();


    }
}
