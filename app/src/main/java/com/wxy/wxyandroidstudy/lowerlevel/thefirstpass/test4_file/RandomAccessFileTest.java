package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test4_file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {
    public static void main(String[] args) {
       String path = "/Users/wangxianyou/Desktop/6.6.3/hzzx002/release/yyyyy.txt";
        File file = new File(path);
        try {
            initialWrite(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            readFile(path);
            readFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readFile(String fileName) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        int counter = raf.readInt();
        boolean b = raf.readBoolean();
        String msg = raf.readUTF();

        System.out.println(counter);
        System.out.println(msg);
        incrementReadCounter(raf);
        raf.close();
    }

    public static void incrementReadCounter(RandomAccessFile raf)
            throws IOException {
        long currentPosition = raf.getFilePointer();
        System.out.println(currentPosition);
        raf.seek(0);
        System.out.println(raf.getFilePointer());
        int counter = raf.readInt();
        counter++;
        raf.seek(0);
        raf.writeInt(counter);
        raf.seek(currentPosition);
    }

    public static void initialWrite(String fileName) throws IOException {
        System.out.println("走进了");
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        raf.writeInt(0);
        raf.writeBoolean(true);
        raf.seek(10);
        raf.writeUTF("kkkkk");
        raf.close();
    }

}
