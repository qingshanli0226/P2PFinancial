package com.example.day_022;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.net.ServerSocket;

public class MyServer extends SQLiteOpenHelper {
    public MyServer(@Nullable Context context) {
        super(context, "my.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table User(_id integer primary key autoincrement,name varchar(50),age integer)");
        db.execSQL("insert into User values(null,'武1',10)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
