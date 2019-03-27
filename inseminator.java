package com.example.user.smartfarmer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class inseminator extends AppCompatActivity {
DatabaseHelper db;
RadioGroup g2;
Button btnsave;
RadioButton radiotype1;

EditText mytype,myname,myemail,mylocation,myphone,aicode,pass,cnfpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inseminator);
        int selected = g2.getCheckedRadioButtonId();
        radiotype1 = (RadioButton)findViewById(selected);
        myname= (EditText)findViewById(R.id.name);
        myemail= (EditText)findViewById(R.id.email);
        mylocation= (EditText)findViewById(R.id.location);
        myphone= (EditText)findViewById(R.id.phone);
        aicode =(EditText)findViewById(R.id.aicode);
        pass = (EditText)findViewById(R.id.password);
        cnfpass= (EditText)findViewById(R.id.confirmpassword);
        btnsave = (Button)findViewById(R.id.btnsave);
        db = new DatabaseHelper(this, "aidb.sqlite", null, 1);
        db.querydata("CREATE TABLE IF NOT EXISTS inseminator(id INTEGER PRIMARY KEY AUTOINCREMENT,type VARCHAR , name VARCHAR,email VARCHAR,location VARCHAR,phone VARCHAR,AIcode VARCHAR,password VARCHAR)");

btnsave.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        try {
db.addinseminator(
        radiotype1.getText().toString().trim(),
        myemail.getText().toString().trim(),
        myname.getText().toString().trim(),
        mylocation.getText().toString().trim(),
        myphone.getText().toString().trim(),
        aicode.getText().toString().trim(),
        pass.getText().toString().trim()
);

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
        }
    }
});
    }

}
