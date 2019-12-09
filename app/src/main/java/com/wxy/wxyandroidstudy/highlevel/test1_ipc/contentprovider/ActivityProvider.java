package com.wxy.wxyandroidstudy.highlevel.test1_ipc.contentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.apkfuns.logutils.LogUtils;
import com.wxy.bean.Book;
import com.wxy.bean.User;
import com.wxy.wxyandroidstudy.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author wxy
 * @description:
 * @date :2019-12-09 15:52
 */
public class ActivityProvider extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        Uri bookUri = Uri.parse("content://com.wxy.wxyandroidstudy.mprovider/book");
        ContentResolver bookResolver = getContentResolver();
        ContentValues bookValues = new ContentValues();
        bookValues.put("_id",0);
        bookValues.put("name","开发艺术探索");
        bookResolver.insert(bookUri,bookValues);
        Cursor bookQuery = bookResolver.query(bookUri, null, null, null, null);
        if (bookQuery!= null) {
            while (bookQuery.moveToNext()) {
                Book book = new Book(bookQuery.getInt(0),bookQuery.getString(1));
                LogUtils.e(book);
            }
            bookQuery.close();
        }
        Uri userUri = Uri.parse("content://com.wxy.wxyandroidstudy.mprovider/user");
        ContentResolver userResolver = getContentResolver();
        ContentValues userValues = new ContentValues();
        userValues.put("_id",0);
        userValues.put("name","wxy");
        userResolver.insert(userUri,userValues);
        Cursor userQuery = userResolver.query(userUri, null, null, null, null);
        if (userQuery!= null) {
            while (userQuery.moveToNext()) {
                User book = new User(userQuery.getString(1),11,true);
                LogUtils.e(book);
            }
            userQuery.close();
        }
    }
}
