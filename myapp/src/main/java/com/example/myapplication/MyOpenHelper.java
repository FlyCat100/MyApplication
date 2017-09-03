package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 25737 on 2017/8/24.
 */

public class MyOpenHelper extends SQLiteOpenHelper {
    public final static String DATABASE_TABLE="user";
    public MyOpenHelper(Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(_id integer primary key autoincrement,name varchar(20),pwd varchar(20)) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        System.out.println("onUpgrade");
    }
}
