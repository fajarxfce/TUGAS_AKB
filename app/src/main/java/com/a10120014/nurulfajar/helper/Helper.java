package com.a10120014.nurulfajar.helper;

/*
Nama    : Nurul Fajar
NIM     : 10120014
Kelas   : IF-1
Matkul  : Aplikasi Komputer Bergerak
*/


import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Helper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "10120014_Nurul_Fajar";


    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TABLE = "CREATE TABLE users (id INTEGER PRIMARY KEY autoincrement, " +
                "title TEXT NOT NULL, " +
                "category TEXT NOT NULL, " +
                "description TEXT NOT NULL, " +
                "created_at datetime default current_timestamp," +
                "updated_at datetime default current_timestamp)";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        onCreate(sqLiteDatabase);
    }

    public ArrayList<HashMap<String, String>> GetAll(){

        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String QUERY = "SELECT * FROM users";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(QUERY, null);

        if (cursor.moveToFirst()){
            do {
                HashMap<String,String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("title", cursor.getString(1));
                map.put("category", cursor.getString(2));
                map.put("description", cursor.getString(3));
                map.put("created_at", cursor.getString(4));
                map.put("updated_at", cursor.getString(5));

                list.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void Insert(String Title, String Category, String Description){
        SQLiteDatabase database = this.getWritableDatabase();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        String QUERY = "INSERT INTO users(title, category, description, created_at, updated_at) VALUES('"+Title+"', '"+Category+"', '"+Description+"', '"+date+"', null)";
        database.execSQL(QUERY);
    }

    public void Update(int Id, String Title, String Category, String Description){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "UPDATE users SET title = '"+Title+"', category = '"+Category+"', description = '"+Description+"', updated_at = '"+date+"' WHERE id =" + Id;
        database.execSQL(QUERY);
    }

    public void Delete(int Id){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "DELETE FROM users WHERE id = " +Id;
        database.execSQL(QUERY);
    }



}
