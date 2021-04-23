package com.example.codese_android01_todo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.codese_android01_todo.model.TodoModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {


    private final String TABLE_NAME = "todo";
    private SQLiteDatabase sqLiteDatabase;
    private MyDatabase myDatabase;

    private static DatabaseUtils databaseUtils;

    private DatabaseUtils(Context context) {
        myDatabase = new MyDatabase(context);
    }

    public static DatabaseUtils getInstance(Context context) {
        if (databaseUtils == null) {
            databaseUtils = new DatabaseUtils(context);
        }
        return databaseUtils;
    }

    public List<TodoModel> getListModel() {
        sqLiteDatabase = myDatabase.getReadableDatabase();
        List<TodoModel> todoModels = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            String tag = cursor.getString(3);
            String showtime = cursor.getString(4);

            TodoModel newModel = new TodoModel(id, title, content, tag, showtime);
            todoModels.add(newModel);
            cursor.moveToNext();
        }
        return todoModels;
    }

    public void AddModel(String title, String tag, String content, String date, int id) {
        sqLiteDatabase = this.myDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("title", title);
        contentValues.put("content", content);
        contentValues.put("tag", tag);
        contentValues.put("showtime", date);
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

    }

    public void upDate(String title, String tag, String content, String date, int id) {
        sqLiteDatabase = myDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("title", title);
        contentValues.put("content", content);
        contentValues.put("tag", tag);
        contentValues.put("showtime", date);
        sqLiteDatabase.update(TABLE_NAME, contentValues,
                " id = " + id, null);
    }

}
