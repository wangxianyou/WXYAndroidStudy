package com.wxy.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author wxy
 * @description:
 * @date :2020/9/2 3:38 PM
 */
class News implements Parcelable {
    private int id;
    public News() {
    }


    protected News(Parcel in) {
        id = in.readInt();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
    }
}
