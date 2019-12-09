// IMyAidlInterface.aidl
package com.wxy.bean;

// Declare any non-default types here with import statements
import com.wxy.bean.Book;
interface IOnNewBookArrivedListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onNewBookArrived(in Book book);
}
