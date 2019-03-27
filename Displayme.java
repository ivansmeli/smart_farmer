package com.example.user.smartfarmer;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Displayme extends AppCompatActivity {
ListView lst;
ArrayList<String> arrayList;
TextView tse;
ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayme);
        lst=(ListView)findViewById(R.id.list);
        tse=(TextView)findViewById(R.id.txt);
        arrayList=new ArrayList<>();
        String sql="select * from farmers_details";
        try {
     Cursor c = register.db.fetchdata();
    if(c.getCount()==0){
        Toast.makeText(this, "Empty Tables", Toast.LENGTH_SHORT).show();
    }
    else{
        while(c.moveToNext()){
            arrayList.add(c.getString(2));
        }
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        lst.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}catch(Exception e){
    tse.setText(""+e.getLocalizedMessage());
    Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
}


    }
}
