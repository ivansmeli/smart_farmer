package com.example.user.smartfarmer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class postdetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postdetails);
      Spinner myspinner =(Spinner)findViewById(R.id.spinner2);
      ArrayAdapter<String> myadapter1=new ArrayAdapter<>(postdetails.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.breed));
      myadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      myspinner.setAdapter(myadapter1);
    }
}
