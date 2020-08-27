package com.wxy.utils;

import android.app.Activity;
import android.view.View;

import com.wxy.annotion.InjectView;

import java.lang.reflect.Field;

/**
 * @author wxy
 * @description:
 * @date :2020/8/27 4:11 PM
 */
public class InjectViewUtils {
    public static void init(Activity activity){
        Class<? extends Activity> cls = activity.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectView.class)) {
                InjectView annotation = field.getAnnotation(InjectView.class);
                int value = annotation.value();
                View view = activity.findViewById(value);
                field.setAccessible(true);
                try {
                    field.set(activity,view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
