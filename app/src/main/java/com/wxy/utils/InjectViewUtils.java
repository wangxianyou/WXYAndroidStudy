package com.wxy.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;

import com.wxy.annotation.InjectExtraView;
import com.wxy.annotation.InjectView;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author wxy
 * @description:
 * @date :2020/8/27 4:11 PM
 */
public class InjectViewUtils {
    public static void initBindView(Activity activity){
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

    public static void injectAutowired(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();
        //获得数据
        Intent intent = activity.getIntent();
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }

        //获得此类所有的成员
        Field[] declaredFields = cls.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(InjectExtraView.class)) {
                InjectExtraView autowired = field.getAnnotation(InjectExtraView.class);
                //获得key
                String key = TextUtils.isEmpty(autowired.value()) ? field.getName() : autowired.value();

                if (extras.containsKey(key)) {
                    Object obj = extras.get(key);
                    // todo Parcelable数组类型不能直接设置，其他的都可以.
                    //获得数组单个元素类型
                    Class<?> componentType = field.getType().getComponentType();
                    //当前属性是数组并且是 Parcelable（子类）数组
                    if (field.getType().isArray() &&
                            Parcelable.class.isAssignableFrom(componentType)) {
                        Object[] objs = (Object[]) obj;
                        //创建对应类型的数组并由objs拷贝

                        Object[] objects = Arrays.copyOf(objs, objs.length, (Class<? extends Object[]>) field.getType());
                        obj = objects;
                    }

                    field.setAccessible(true);
                    try {
                        field.set(activity, obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void initExtraView(Activity activity){
        Class<? extends Activity> cls = activity.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectExtraView.class)) {
                InjectExtraView extraView = field.getAnnotation(InjectExtraView.class);
                String value = extraView.value();
                Object simpleName = field.getGenericType().toString();
                Object result = null;
                if ("class java.lang.String".equals(simpleName)) {
                    result = activity.getIntent().getStringExtra(value);
                }else if ("boolean".equals(simpleName)){
                    result = activity.getIntent().getBooleanExtra(value,true);
                }
                if (result == null) {
                    continue;
                }
                field.setAccessible(true);
                try {
                    field.set(activity,result);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
