package com.wxy.bean;

import java.util.List;

public class BaseBean<T>{
    private String msgcode= "";
    private String message= "";
    private List<T> data;

    public String getMsgcode() {
        return msgcode;
    }

    public String getMessage() {
        return message;
    }

    public List<T> getData() {
        return data;
    }
}
