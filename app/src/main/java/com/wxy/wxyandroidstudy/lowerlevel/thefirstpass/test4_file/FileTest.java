package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test4_file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileTest {
    // 复制文件
    public static void copyFile(File sourceFile, File targetFile)
            throws IOException {
        // 新建文件输入流并对它进行缓冲
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff = new BufferedInputStream(input);

        // 新建文件输出流并对它进行缓冲
        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff = new BufferedOutputStream(output);

        // 缓冲数组
        byte[] b = new byte[1024];
        int len;
        while ((len = inBuff.read(b)) != -1) {
            outBuff.write(b, 0, len);
        }
        // 刷新此缓冲的输出流
        outBuff.flush();

        //关闭流
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
    }


    public static void readFileByByte(String path) {
        File file = new File(path);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream("/Users/wangxianyou/Desktop/6.6" +
                    ".3/hzzx002/release/ccc.apk");
            int start;
            while ((start = inputStream.read()) != -1) {
                outputStream.write(start);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        } finally {
            if (inputStream != null && outputStream != null) {
                try {
                    inputStream.close();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void readFileByChar(String source, String des){
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(new File(source)));
            writer = new BufferedWriter(new FileWriter(des));
            String temp;
            while ((temp = reader.readLine()) != null){
                System.out.println(temp);
                writer.write(temp+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public static void savePic(String path, String des) {
        FileOutputStream out = null;
        BufferedInputStream in = null;
        HttpURLConnection connection = null;
        byte[] buf = new byte[1024];
        int len = 0;
        try {
            URL url = new URL(path);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            in = new BufferedInputStream(connection.getInputStream());
            out = new FileOutputStream(des );
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args) {

        String path = "/Users/wangxianyou/Desktop/6.6.3/hzzx002/release/CNS_6.6.405_hzzx002.apk";
        String pathDes = "/Users/wangxianyou/Desktop/6.6.3/hzzx002/release/dddd.apk";
        String pathDesPic = "/Users/wangxianyou/Desktop/6.6.3/hzzx002/release/yyyyy.txt";
        String source = "/Users/wangxianyou/Desktop/6.6.3/hzzx002/release/aaaaa";
        String pathDesPic2 = "/Users/wangxianyou/Desktop/6.6.3/hzzx002/release";
        String netPic = "http://www.chinanews.com/shipin/spfts/20190813/U817P4T309D2278F22450DT20190813151132.jpg";
        String netPic2 = "http://www.chinanews.com/2019/08-22/U610P4T8D8934076F107DT20190822085353.jpg";
//        savePic(netPic, pathDesPic);
        readFileByChar(source, pathDesPic);
//        System.out.println("--------保存图片完成-------");
//        readFileByByte(path);
//        System.out.println("--------readFileByByte-------");
//        try {
//            copyFile(new File(path),new File(pathDes));
//        } catch (IOException e) {
//            System.out.println(e.toString());
//            e.printStackTrace();
//        }

        System.out.println("Hello");
    }
}
