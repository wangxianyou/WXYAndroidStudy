package com.wxy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @author wxy
 * @description:
 * @date :2019-12-09 15:35
 */
public class MyDb extends SQLiteOpenHelper {
    private static final int  DB_VERSION = 1;
    private static final String  DB_NAME = "db_info.db";
    private static final  String CREADT_BOOK = "create table if not exists book(_id integer primary key,name text)";
    private static final  String CREADT_USER = "create table if not exists user(_id integer primary key,name text)";

    public MyDb(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREADT_BOOK);
        db.execSQL(CREADT_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
