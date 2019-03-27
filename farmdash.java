package com.example.user.smartfarmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class farmdash extends AppCompatActivity {
Button myservices,mynews,mymarket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmdash);
        myservices=(Button)findViewById(R.id.services);
        mymarket=(Button)findViewById(R.id.market);
        mynews=(Button)findViewById(R.id.news);
        myservices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent services=new Intent(farmdash.this,postdetails.class);
                startActivity(services);
            }
        });
        mymarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sell=new Intent(farmdash.this,sell.class);
                startActivity(sell);
            }
        });

    }
}
