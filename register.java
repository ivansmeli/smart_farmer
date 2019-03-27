package com.example.user.smartfarmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class register extends AppCompatActivity {
    EditText myname,myphone;
    EditText myemail;
    EditText mylocation;
    EditText pass;
    EditText cnfpass;
    RadioButton radiotype;
    TextView mylogin;
    public static DatabaseHelper db;
    Button mybtnsave;
    RadioGroup g1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this, "aidb.sqlite", null, 1);
        db.querydata("CREATE TABLE IF NOT EXISTS farmers_details(id INTEGER PRIMARY KEY AUTOINCREMENT,type VARCHAR , name VARCHAR,email VARCHAR,phone VARCHAR,location VARCHAR,password VARCHAR)");
g1 = (RadioGroup)findViewById(R.id.G3);
        myname=(EditText)findViewById(R.id.name);
        myemail=(EditText)findViewById(R.id.email);
        mylocation=(EditText)findViewById(R.id.location);
        pass=(EditText)findViewById(R.id.password);
        myphone= (EditText)findViewById(R.id.phone);
        cnfpass=(EditText)findViewById(R.id.confirmpassword);
        mylogin=(TextView)findViewById(R.id.btnlogin);
        mybtnsave=(Button)findViewById(R.id.btnsave);

        g1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.inseminator) {
                    Intent inse= new Intent(register.this, inseminator.class);
                    startActivity(inse);
                }
                else{
                  startActivity(new Intent(getApplicationContext(),register.class));
                }
            }
        });
        mylogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Intent saveIntent=new Intent(register.this,MainActivity.class);
        startActivity(saveIntent);
            }
        });
        mybtnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected = g1.getCheckedRadioButtonId();
                radiotype = (RadioButton)findViewById(selected);

        String email=myemail.getText().toString().trim();
        String  name=myname.getText().toString().trim();
        String location=mylocation.getText().toString().trim();
        String pwd=pass.getText().toString().trim();
        String cpwd=cnfpass.getText().toString().trim();
                if (email.equals("") || pwd.equals("") || mylocation.equals("")||myname.equals("")) {
                    Toast.makeText(register.this, "Empty Fields are NOT ALLOWED", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        db.addfarmer(
                                radiotype.getText().toString().trim(),
                                myemail.getText().toString().trim(),
                                myname.getText().toString().trim(),
                                myphone.getText().toString().trim(),
                                mylocation.getText().toString().trim(),
                                pass.getText().toString().trim()
                        );

                        Toast.makeText(register.this, " Data Saved successfully", Toast.LENGTH_SHORT).show();
                        myemail.setText("");
                        mylocation.setText("");
                        myphone.setText("");
                        myname.setText("");
                        pass.setText("");
                        new Intent(getApplicationContext(),MainActivity.class);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_SHORT).show();
                    }
                }
                }
        });
    }
}
