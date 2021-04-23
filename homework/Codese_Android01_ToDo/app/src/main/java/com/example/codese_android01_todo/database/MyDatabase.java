package com.example.codese_android01_todo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "todo.db";
    private static final int DATA_VERSION = 1;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATA_VERSION);
    }
}