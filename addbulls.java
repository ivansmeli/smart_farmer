package com.example.user.smartfarmer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addbulls extends AppCompatActivity {
Button btnsave;
DatabaseHelper db;
EditText myname,myaicode,mylongevity,myweight,mymilk,myprotein,mydam,mysire,myprice,myfertility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbulls);
        db= new DatabaseHelper(this,"aidb.sqlite",null,1);
        db.querydata("CREATE TABLE IF NOT EXISTS bull_catalog(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,aicode VARCHAR,longevity INTEGER ,weight DOUBLE,milk DOUBLE,protein DOUBLE,dam VARCHAR,sire VARCHAR,price DOUBLE,fertility DOUBLE)");
        myname=(EditText)findViewById(R.id.name);
        myaicode=(EditText)findViewById(R.id.aicode);
        mylongevity=(EditText)findViewById(R.id.longevity);
        myweight=(EditText)findViewById(R.id.weight);
        mymilk=(EditText)findViewById(R.id.milk);
        myprotein=(EditText)findViewById(R.id.protein);
        mydam=(EditText)findViewById(R.id.dam);
        mysire=(EditText)findViewById(R.id.sire);
        myprice=(EditText)findViewById(R.id.price);
        myfertility=(EditText)findViewById(R.id.fertility);
        btnsave=(Button)findViewById(R.id.save);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    db.addbullcatalogue(
                            myname.getText().toString().trim(),
                            myaicode.getText().toString().trim(),
                            mylongevity.getText().toString().trim(),
                            myweight.getText().toString().trim(),
                            mymilk.getText().toString().trim(),
                            myprotein.getText().toString().trim(),
                            mydam.getText().toString().trim(),
                            mysire.getText().toString().trim(),
                            myprice.getText().toString().trim(),
                            myfertility.getText().toString().trim()
                    );

                    Toast.makeText(addbulls.this, " Data Saved successfully", Toast.LENGTH_SHORT).show();
                    myname.setText("");
                    myaicode.setText("");
                    mydam.setText("");
                    myname.setText("");
                    myprotein.setText("");
                    myfertility.setText("");
                    myprice.setText("");
                    mysire.setText("");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
