package com.example.user.smartfarmer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class sell extends AppCompatActivity {
    RadioGroup g1;
    EditText mybreed, myage, myprice;
    Button savedata,viewsales;
    ImageView myimageview;
    public static DatabaseHelper databaseHelper;
    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" New Record");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        mybreed = (EditText) findViewById(R.id.breed);
        myage = (EditText) findViewById(R.id.age);
        myprice = (EditText) findViewById(R.id.price);
        savedata = (Button) findViewById(R.id.save);
        viewsales = (Button) findViewById(R.id.viewsales);
        myimageview = (ImageView) findViewById(R.id.imageView2);
        final Spinner myspinner = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> myadapter1 = new ArrayAdapter<>(sell.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.category));
        myadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(myadapter1);
        g1 = (RadioGroup) findViewById(R.id.G2);
        databaseHelper = new DatabaseHelper(this, "aidb.sqlite", null, 1);
        databaseHelper.querydata("CREATE TABLE IF NOT EXISTS soldanimals(id INTEGER PRIMARY KEY AUTOINCREMENT, category VARCHAR, breed VARCHAR, age INTEGER, price INTEGER ,image BLOB)");
        g1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.animalpdct) {
                    Intent sellpdct = new Intent(sell.this, sellpdct.class);
                    startActivity(sellpdct);
                }
            }
        });


        myimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//read external storage
                ActivityCompat.requestPermissions(
                        sell.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });
         viewsales.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

//      start
        startActivity(new Intent(sell.this,RecordListActivity.class));


    }
});

        savedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    databaseHelper.insertanimalsales(
                            myspinner.getSelectedItem().toString().trim(),
                            mybreed.getText().toString().trim(),
                            myage.getText().toString().trim(),
                            myprice.getText().toString().trim(),
                            imageViewToByte(myimageview));

                    Toast.makeText(sell.this,"DATA SAVED  SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                    myage.setText("");
                    mybreed.setText("");
                    myprice.setText("");
                    myimageview.setImageResource(R.drawable.addphoto1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public static byte [] imageViewToByte(ImageView image){
        Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte []bytearray = stream.toByteArray();
        return bytearray;
    }



        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults){
            if (requestCode == REQUEST_CODE_GALLERY) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    galleryIntent.setType("image/*");
                    startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
                } else {
                    Toast.makeText(this, "You DON'T HAVE PERMISSION", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
            if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
                Uri uri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    myimageview.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            super.onActivityResult(requestCode, resultCode, data);

        }
    }


