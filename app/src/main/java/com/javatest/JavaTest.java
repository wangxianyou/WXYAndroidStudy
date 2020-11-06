package com.javatest;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * @author wxy
 * @description:
 * @date :2020-07-22 13:07
 */
public class JavaTest {

    public static void main(String[] args) throws Exception {
//        com.javatest.JavaTest test = new com.javatest.JavaTest();
//        test.valueOfStr("ABCD");
//        System.out.println(test.valueOfStr("ZZZZZZZZZZZZBACD"));


//        View rootView = new View(0, 0, 480, 720);
//        View view1 = new View(0, 0, 200, 200);
//        View view2 = new View(0, 200, 240, 520);
//        View view3 = new View(240, 200, 240, 520);
//        rootView.childViews.add(view1);
//        rootView.childViews.add(view2);
//        rootView.childViews.add(view3);
//        View view11 = new View(0, 0, 80, 80);
//        View view12 = new View(100, 100, 100, 100);
//        View view13 = new View(100, 100, 50, 50);
//        view1.childViews.add(view11);
//        view1.childViews.add(view12);
//        view1.childViews.add(view13);
//        findMostVisibleView(rootView);
//        System.out.println(findMostVisibleView(rootView).toString());

        MyThread thread = new MyThread(null);
        thread.setName("线程---A");
        MyThread thread1 = new MyThread(thread);
        thread1.setName("线程---B");
        MyThread thread2 = new MyThread(thread1);
        thread2.setName("线程---C");
        thread.start();
        thread1.start();
        thread2.start();
    }

    static class MyThread extends Thread {
        Thread t;

        public MyThread(Thread t) {
            this.t = t;
        }

        @Override
        public void run() {
            super.run();
            try {
                if (t == null) {
                    for (int i = 0; i < 50; i++) {
                        sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                    }
                }else {
                    t.join();
                    for (int i = 0; i < 50; i++) {
                        sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    int valueOfStr(String str) {
        if (str.isEmpty()) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        int size = str.length();
        for (int i = 0; i < size; i++) {
            int item = str.charAt(i) - 65;
            System.out.println(item);
            sb.append(item);
        }
        return "2147483647".compareTo(sb.toString()) >= 0 ? Integer.parseInt(sb.toString()) : 2147483647;
    }

    static class View {
        public int x;
        public int y;
        public int width;
        public int height;
        public List<View> childViews = new ArrayList<>();

        public View(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        @NonNull
        @Override
        public String toString() {
            return "View(with:" + width + ",height:" + height + ")";
        }
    }


    static View findMostVisibleView(View rootView) {
        if (rootView.childViews == null || rootView.childViews.size() == 0) {
            return rootView;
        }
        int size = rootView.childViews.size();
        int rootSize = rootView.width * rootView.height;
        int allChildSize = 0;
        View firstView = rootView.childViews.get(0);
        int currMaxSize = firstView.height * firstView.width;
        View currentMaxView = null;
        for (int i = 0; i < size; i++) {
            View item = rootView.childViews.get(i);
            int currentSize = item.height * item.width;
            if (currentSize > currMaxSize) {
                currentMaxView = item;
            }
            allChildSize += item.height * item.width;
        }

        if (rootSize > allChildSize) {
            return rootView;
        }
        return currentMaxView == null ? firstView : findMostVisibleView(currentMaxView);
    }


    static View findMostVisibleView1(View rootView) {
        if (rootView.childViews == null || rootView.childViews.size() == 0) {
            return rootView;
        }
        int size = rootView.childViews.size();
        int rootSize = rootView.width * rootView.height;
        int allChildSize = 0;
        View firstView = rootView.childViews.get(size - 1);
        int currMaxSize = firstView.height * firstView.width;
        View currentMaxView = null;
        for (int i = size - 2; i >= 0; i--) {
            View item = rootView.childViews.get(i);
            int currentSize = item.height * item.width;
            if (currentSize > currMaxSize) {
                currentMaxView = item;
            }
            allChildSize += item.height * item.width;
        }

        if (rootSize > allChildSize) {
            return rootView;
        }
        return currentMaxView == null ? firstView : findMostVisibleView(currentMaxView);
    }
}
