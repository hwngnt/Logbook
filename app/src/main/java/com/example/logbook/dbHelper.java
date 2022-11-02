package com.example.logbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class dbHelper extends SQLiteOpenHelper {
    private SQLiteDatabase database;

    private static final String TABLE_IMG = "Image";
    public static final String IMG_ID = "img_id";
    public static final String IMG_LINK = "img_link";


    private static final String TABLE_IMG_CREATE = String.format(
            "CREATE TABLE %s (" +
                    "   %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "   %s TEXT)",
            TABLE_IMG, IMG_ID, IMG_LINK);

    public dbHelper(Context context) {
        super(context, TABLE_IMG, null, 1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_IMG_CREATE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMG);
        Log.v(this.getClass().getName(), TABLE_IMG + " database upgrade to version " +
                newVersion + " - old data lost");
        onCreate(db);
    }
    public long insertIMG(String img_link) {
        ContentValues rowValues = new ContentValues();
        rowValues.put(IMG_LINK, img_link);
        return database.insertOrThrow(TABLE_IMG, null, rowValues);
    }


    public String[] getAllIMG() {
        Cursor cursor = database.query(TABLE_IMG, new String[] {IMG_ID, IMG_LINK},
                null, null, null, null, IMG_ID);

        int length  = cursor.getCount();
        String[] listImg = new String[length];
        int count = 0;

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String link = cursor.getString(1);

            listImg[count] = link;
            count ++;
            cursor.moveToNext();
        }

        return listImg;
    }


}