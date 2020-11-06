package com.javatest;

import java.util.Arrays;

/**
 * @author wxy
 * @description:
 * @date :2020/10/27 1:59 PM
 */
class JavaTest1 {
    public static void main(String[] args) {
//        User u1 = new User("w",18,true);
//        User u2 = new User("w",19,true);
//        User u3 = u1;
//        u3.setAge(13);
//        System.out.println(u1.hashCode());
//        System.out.println(u2.hashCode());
//        System.out.println(u3.hashCode());
//        System.out.println(u1.equals(u3));

//        int[] nums1 = {1,2,3,0,0,0};
//        int m = 3;
//        int[] nums2 = {2,5,6};
//        int n = 3;
//        merge(nums1,m,nums2,n);

        String str = "aabb";
        char c = firstUniqChar(str);
        System.out.println(c);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] arr = new int[m+n];
        for(int i = 0;i < m;i++){
            arr[i] = nums1[i];
        }
        for(int j = 0;j < n;j++){
            arr[j+m] = nums2[j];
        }
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static char firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return ' ';
        }
        int[] a = new int[256];
        char[] chars = s.toCharArray();

        return ' ';
    }
}
