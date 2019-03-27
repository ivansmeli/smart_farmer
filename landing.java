package com.example.user.smartfarmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class landing extends AppCompatActivity {
Button myservices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
myservices.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent post=new Intent(landing.this,postdetails.class);
        startActivity(post);
    }
});
    }
}
