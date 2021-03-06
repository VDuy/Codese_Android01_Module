package com.example.lesson6.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;



import com.example.lesson6.models.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {
    private static final String TAG = "get_list_topic" ;

    public final String TABLE_SHORT_STORY = "tbl_short_story";

    private SQLiteDatabase sqLiteDatabase;
    private MyDatabase myDatabase;

    private static DatabaseUtils databaseUtils;

    private DatabaseUtils(Context context){myDatabase = new MyDatabase(context);}
    public static DatabaseUtils getInstance(Context context){
        if(databaseUtils == null){
            databaseUtils = new DatabaseUtils(context);
        }
        return databaseUtils;
    }
    public List<ItemModel> getListTopic() {
        sqLiteDatabase = myDatabase.getReadableDatabase();
        List<ItemModel> itemModels = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM " + TABLE_SHORT_STORY, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String image = cursor.getString(1);
            String title = cursor.getString(2);
            String description = cursor.getString(3);
            String author = cursor.getString(5);

            ItemModel itemModel = new ItemModel(id, image, title, description, author);
            itemModels.add(itemModel);

            cursor.moveToNext();
        }
        Log.d(TAG, "getListTopic: " + itemModels);
        return itemModels;
    }
}
