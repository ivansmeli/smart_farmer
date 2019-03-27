package com.example.user.smartfarmer;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class RecordListActivity extends AppCompatActivity {
ListView mlistview;
ArrayList<Model>mList;
RecordListAdapter mAdapter = null;
ImageView imageViewIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" Record List");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);
        mlistview=(ListView)findViewById(R.id.listView);
        mList = new ArrayList<>();
        mAdapter = new RecordListAdapter(this,R.layout.row,mList);
        mlistview.setAdapter(mAdapter);

        Cursor cursor = sell.databaseHelper.getdata("SELECT * FROM soldanimals");
        mList.clear();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String category = cursor.getString(1);
            String breed = cursor.getString(2);
            String age = cursor.getString(3);
            String price = cursor.getString(4);
            byte[] image = cursor.getBlob(5);
            mList.add( new Model(id,category,breed,age,price,image));
        }
        mAdapter.notifyDataSetChanged();
        if(mList.size() == 0){
//if no record in the database  table
           Toast.makeText(this,"No Record Available",Toast.LENGTH_SHORT).show();
        }
        mlistview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                CharSequence[] items = {"Update","Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(RecordListActivity.this);
                dialog.setTitle("Choose an Action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if(i == 0){
                        Cursor c = sell.databaseHelper.getdata("SELECT id FROM soldanimals");
                        ArrayList<Integer>ArrID = new ArrayList<>();
                        while(c.moveToNext()){
                            ArrID.add(c.getInt(0));
                        }
                          showDialogUpdate(RecordListActivity.this,ArrID.get(position));
                        }
                        if(i == 1){
                            Cursor c = sell.databaseHelper.getdata("SELECT id FROM soldanimals");
                            ArrayList<Integer>ArrID = new ArrayList<>();
                            while(c.moveToNext()){
                                ArrID.add(c.getInt(0));
                            }
                            showDialogDelete(ArrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;

            }
        });




    }

    private void showDialogDelete(final Integer idrecord) {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(RecordListActivity.this);
dialogDelete.setTitle("Warning");
dialogDelete.setMessage("Are You Sure You want To Delete");
dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
        try{
          sell.databaseHelper.deleteData(idrecord);
          Toast.makeText(RecordListActivity.this,"DELETED SUCCESFULLY",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
Log.e("Error",e.getMessage());
        }
        updateRecordList();
    }
});
dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int i) {
        dialog.dismiss();
    }
});
dialogDelete.show();
    }

    private void showDialogUpdate(final Activity activity, final int position){
 final Dialog dialog = new Dialog(activity);
dialog.setContentView(R.layout.update_dialog);
dialog.setTitle("Update");
imageViewIcon = dialog.findViewById(R.id.imageViewRecord);
        final EditText edtbreed=(EditText)dialog.findViewById(R.id.edtBreed);
        final EditText edtage=(EditText)dialog.findViewById(R.id.edtAge);
        final EditText edtprice=(EditText)dialog.findViewById(R.id.edtprice);
        Button btnupdate = (Button)dialog.findViewById(R.id.update);
        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels*0.95);
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels*0.7);
        dialog.getWindow().setLayout(width,height);
        dialog.show();
        imageViewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        RecordListActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                 sell.databaseHelper.updateData(
                         edtbreed.getText().toString().trim(),
                         edtage.getText().toString().trim(),
                         edtprice.getText().toString().trim(),
                         sell.imageViewToByte(imageViewIcon),
                         position
                 );
                 dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Data Updated Successfully ", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Log.e("Update Error",e.getMessage());
                }
            }
        });
        updateRecordList();



    }

    private void updateRecordList() {
        Cursor cursor = sell.databaseHelper.getdata("SELECT * FROM soldanimals");
        mList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String category = cursor.getString(1);
            String breed = cursor.getString(2);
            String age = cursor.getString(3);
            String price = cursor.getString(4);
            byte[] image = cursor.getBlob(5);
            mList.add( new Model(id,category,breed,age,price,image));
        }
        mAdapter.notifyDataSetChanged();
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
        if (requestCode == 888) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 888);
            } else {
                Toast.makeText(this, "You DON'T HAVE PERMISSION", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
        if (requestCode == 888 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewIcon.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
