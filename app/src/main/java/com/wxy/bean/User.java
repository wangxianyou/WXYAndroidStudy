package com.wxy.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @author wxy
 * @description:
 * @date :2019-12-03 15:58
 */
public class User implements Parcelable , Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private boolean isStudent;


    public User(String name, int age, boolean isStudent) {
        this.name = name;
        this.age = age;
        this.isStudent = isStudent;
    }

    protected User(Parcel in) {
        age = in.readInt();
        name = in.readString();
        isStudent = in.readInt() == 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeString(name);
        dest.writeInt(isStudent?0:1);
    }
}
