package com.example.user.smartfarmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnlogin,btnreg;
    DatabaseHelper db;
    TextView forgotpwd;
    EditText myemail ,mypassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnlogin=(Button)findViewById(R.id.login);
        btnreg=(Button) findViewById(R.id.btnregister);
        db=new DatabaseHelper(this,"aidb.sqlite",null,1);
        myemail=(EditText) findViewById(R.id.email);
        mypassword=(EditText)findViewById(R.id.password);
        btnlogin=(Button)findViewById(R.id.login);


        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerIntent=new Intent(MainActivity.this,register.class);
                startActivity(registerIntent);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = myemail.getText().toString().trim();
                String pwd = mypassword.getText().toString().trim();
                try {
                    boolean test = db.verify(user, pwd);
                    if (test == true) {
                        //Boolean res = db.checkuser(user, pwd);
                        //if (user.equals("admin") && pwd.equals("pass")) {

                        Toast.makeText(MainActivity.this, "Admin", Toast.LENGTH_SHORT).show();
                        Intent movetoadmin = new Intent(MainActivity.this, admindash.class);
                        startActivity(movetoadmin);
                    } else {
                        //if (res.equals(true) && item.equals("General public")) {
                        //Toast.makeText(MainActivity.this, "Login Successful-Welcome " + user, Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "Farm", Toast.LENGTH_SHORT).show();

                        Intent landing = new Intent(MainActivity.this, Displayme.class);
                        startActivity(landing);

                        //} else {
                        //Toast.makeText(MainActivity.this, " Wrong  Username or Password ", Toast.LENGTH_SHORT).show();
                        //}

                    }
                }
              catch(Exception r){
                        Toast.makeText(MainActivity.this, "" + r, Toast.LENGTH_SHORT).show();
                    }
                }

        });

    }
}
