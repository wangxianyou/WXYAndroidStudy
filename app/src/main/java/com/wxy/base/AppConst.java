package com.wxy.base;

import android.os.Environment;

public class AppConst {
    public static String BASE_URL = "http://dw.chinanews.com/chinanews/";

    public static final String CHAPTER_2_PATH = Environment
            .getExternalStorageDirectory().getPath()
            + "/wxy/chapter_2/";

    public static final String CACHE_FILE_PATH = CHAPTER_2_PATH + "usercache";

    public static final int MSG_FROM_CLIENT = 0;
    public static final int MSG_FROM_SERVICE = 1;
}
