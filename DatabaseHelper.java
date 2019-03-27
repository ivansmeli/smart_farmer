package com.example.user.smartfarmer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.sql.Statement;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String database_name="aidb.sqlite";
    public static final String table_name="users";
    public static final String col_id="id";
    public static final String col_name="name";
    public static final String col_email="email";
    public static final String col_location ="location";
    public static final String col_password ="password";
    Cursor cursor;
    public DatabaseHelper(Context context,String name ,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,email VARCHAR,location VARCHAR,password VARCHAR)");
    }
    public void addbullcatalogue(String name,String aicode,String longevity,String weight,String milk,String protein,String dam,String sire, String price,String fertility){
        SQLiteDatabase database= getWritableDatabase();
        String sql="INSERT INTO bull_catalog VALUES(NULL,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement=database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,name);
        statement.bindString(2,aicode);
        statement.bindString(3,longevity);
        statement.bindString(4,weight);
        statement.bindString(5,milk);
        statement.bindString(6,protein);
        statement.bindString(7,dam);
        statement.bindString(8,sire);
        statement.bindString(9,price);
        statement.bindString(10,fertility);
statement.executeInsert();
    }

        public  void querydata(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);

}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL("DROP TABLE IF EXISTS "+table_name);
       // onCreate(db);
    }
    public void insertanimalsales(String category,String breed,String age,String price,byte[]image){
SQLiteDatabase database = getWritableDatabase();
String sql="INSERT INTO soldanimals VALUES(NULL,?,?,?,?,?)";
SQLiteStatement statement = database.compileStatement(sql);
statement.clearBindings();
statement.bindString(1,category);
statement.bindString(2,breed);
statement.bindString(3,age);
statement.bindString(4,price);
statement.bindBlob(5,image);
statement.executeInsert();
    }
    public Cursor getdata(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    public Cursor fetchdata( ){
        SQLiteDatabase db=getReadableDatabase();
        String sql="select * from farmers";
        Cursor c=db.rawQuery(sql,null);

        return c;
    }
    public void addfarmer(String type, String myname ,String myemail , String phone ,String mylocation,String mypassword){
        SQLiteDatabase db=getWritableDatabase();
        String sql="INSERT INTO farmers_details VALUES(NULL,?,?,?,?,?,?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,type);
        statement.bindString(2,myname);
        statement.bindString(3,myemail);
        statement.bindString(4,phone);
        statement.bindString(5,mylocation);
        statement.bindString(6,mypassword);
        statement.executeInsert();
    }
    public boolean verify(String username,String password){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from farmers where email=? and password=?", new String[] {username,password});
        if(c.getCount() > 0)
            return true;

        else
            return  false;

    }

    public void updateData(String breed,String age,String price, byte [] image ,int id){
SQLiteDatabase database = getWritableDatabase();
String sql = "UPDATE soldanimals SET breed =? ,age =? ,price =?, image =? WHERE id =? ";
SQLiteStatement statement = database.compileStatement(sql);
        statement.bindString(1,breed);
        statement.bindString(2,age);
        statement.bindString(3,price);
        statement.bindBlob(4,image);
        statement.bindDouble(5,(double)id);
        statement.execute();
        database.close();
    }
    public  void  deleteData(int id){
SQLiteDatabase database = getWritableDatabase();
String sql  ="DELETE FROM soldanimals WHERE id=?";
SQLiteStatement statement = database.compileStatement(sql);
statement.clearBindings();
statement.bindDouble(1, (double)id);
statement.execute();
database.close();
    }
    public  void addinseminator(String type, String myname ,String myemail , String location ,String phone,String aicode,String password){
        SQLiteDatabase db = getWritableDatabase();
        String sql="INSERT INTO inseminators VALUES(NULL,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,type);
        statement.bindString(2,myname);
        statement.bindString(3,myemail);
        statement.bindString(4,location);
        statement.bindString(5,phone);
        statement.bindString(6,aicode);
        statement.bindString(7,password);
        statement.executeInsert();
    }
    }
