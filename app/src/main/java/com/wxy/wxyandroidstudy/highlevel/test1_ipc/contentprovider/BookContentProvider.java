package com.wxy.wxyandroidstudy.highlevel.test1_ipc.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.apkfuns.logutils.LogUtils;
import com.wxy.db.MyDb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author wxy
 * @description:
 * @date :2019-12-09 14:29
 */
public class BookContentProvider extends ContentProvider {
    private static final UriMatcher MATCHER = new UriMatcher(0);
    private static final String TABLE_BOOK = "book";
    private static final String TABLE_USER = "user";
    private static final int TABLE_BOOK_CODE = 0;
    private static final int TABLE_USER_CODE = 1;

    private static final String AUTHOR = "com.wxy.wxyandroidstudy.mprovider";
    private static final String URI_BOOK = "content://com.wxy.wxyandroidstudy.mprovider/book";
    private static final String URI_USER = "content://com.wxy.wxyandroidstudy.mprovider/user";

    private SQLiteDatabase mDb;
    @Override
    public boolean onCreate() {
        LogUtils.e(Thread.currentThread().getName());
        mDb = new MyDb(getContext()).getWritableDatabase();
        MATCHER.addURI(AUTHOR, TABLE_BOOK, TABLE_BOOK_CODE);
        MATCHER.addURI(AUTHOR, TABLE_USER, TABLE_USER_CODE);
        return false;

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        LogUtils.e(Thread.currentThread().getName());
        String table = getTableByUri(uri);
        return mDb.query(table, projection, selection, selectionArgs, sortOrder, null, null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String table = getTableByUri(uri);
        mDb.insert(table,null,values);
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values,
                      @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    public String getTableByUri(Uri uri) {
        String name= null;
        int match = MATCHER.match(uri);
        switch (match) {
            case TABLE_BOOK_CODE:
                name = TABLE_BOOK;
                break;
            case TABLE_USER_CODE:
                name = TABLE_USER;
                break;

            default:
                break;
        }
        return name;
    }
}
