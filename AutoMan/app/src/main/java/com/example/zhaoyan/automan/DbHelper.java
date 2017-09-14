package com.example.zhaoyan.automan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by zhaoyan on 2017/3/25.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static DbHelper dbHelper=null;
    public static  DbHelper getDbhelper(Context context){
        if(dbHelper==null){
            dbHelper=new DbHelper(context);
        }
//        SQLiteDatabase db=dbHelper.getWritableDatabase();
//        db.execSQL("drop table chatmessage");
//        Log.i("AAA", "删除表");
//        db.execSQL("create table if not exists chatmessage (_id integer primary key autoincrement,chatmessageclass text)");
//        Log.i("AAA", "创建表");
//        db.close();
        return dbHelper;
    }

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DbHelper(Context context){
        super(context,"chat.info",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table if not exists chatmessage (_id integer primary key autoincrement,chatmessageclass text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
