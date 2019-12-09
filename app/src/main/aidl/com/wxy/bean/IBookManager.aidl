// IMyAidlInterface.aidl
package com.wxy.bean;

// Declare any non-default types here with import statements
import com.wxy.bean.Book;
import com.wxy.bean.IOnNewBookArrivedListener;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(in IOnNewBookArrivedListener lis);
    void unregisterListener(in IOnNewBookArrivedListener lis);
}
