package com.example.notify.db;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncStats;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.notify.model.NotificationModel;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DB_NAME = "Notify";
    private static String NOTIFICATION_TABLE = "notification_table";
    private static String NOTIFICATION_TITLE = "title";
    private static String NOTIFICATION_MESSAGE = "message";
    private static String NOTIFICATION_TIMESTAMP = "notificationTimeStamp";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + NOTIFICATION_TABLE + " ( " + NOTIFICATION_TITLE + " TEXT, " + NOTIFICATION_MESSAGE + " TEXT,"+  NOTIFICATION_TIMESTAMP + " LONG" + "  )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    synchronized public void insertNotification(String title, String message) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOTIFICATION_TITLE, title);
        contentValues.put(NOTIFICATION_MESSAGE, message);
        contentValues.put(NOTIFICATION_TIMESTAMP, System.currentTimeMillis());
        sqLiteDatabase.insert(NOTIFICATION_TABLE, null, contentValues);
    }


    synchronized public List<NotificationModel> getNotification() {
        List<NotificationModel> notificationModelList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(NOTIFICATION_TABLE, null, null, null, null, null, NOTIFICATION_TIMESTAMP + " DESC");
        if (cursor.moveToFirst()) {
            do {
                NotificationModel notificationModel = new NotificationModel();
                notificationModel.setTitle(cursor.getString(cursor.getColumnIndex(NOTIFICATION_TITLE)));
                notificationModel.setMessage(cursor.getString(cursor.getColumnIndex(NOTIFICATION_MESSAGE)));
                notificationModel.setNotificationTimeStamp(cursor.getLong(cursor.getColumnIndex(NOTIFICATION_TIMESTAMP)));
                notificationModelList.add(notificationModel);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return notificationModelList;
    }
}
