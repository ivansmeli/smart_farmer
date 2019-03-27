package com.example.user.smartfarmer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class sellpdct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellpdct);
        Spinner myspinner1 =(Spinner)findViewById(R.id.spinner4);
        ArrayAdapter<String> myadapter1=new ArrayAdapter<>(sellpdct.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.category2));
        myadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner1.setAdapter(myadapter1);
    }
    }

