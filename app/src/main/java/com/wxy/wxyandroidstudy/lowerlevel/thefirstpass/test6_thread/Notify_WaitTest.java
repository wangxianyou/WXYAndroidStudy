package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test6_thread;

import android.os.Bundle;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Notify_WaitTest extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Producer p = new Producer();
        p.start();
        new Consumer("Consumer1", p).start();
        new Consumer("Consumer2", p).start();
        new Consumer("Consumer3", p).start();
    }


    public class Producer extends Thread {

        List<Message> msgList = new ArrayList<>();

        @Override public void run() {
            try {
                while (true) {
                    Thread.sleep(3000);
                    Message msg = new Message();
                    synchronized(msgList) {
                        msgList.add(msg);
                        msgList.notify(); //这里只能是notify而不能是notifyAll，否则remove(0)会报java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public Message waitMsg() {
            synchronized(msgList) {
                System.out.println("进入 synchronized内部" + getName() );
                if(msgList.size() == 0) {
                    try {
                        msgList.wait();
                        System.out.println("进入 synchronized内部 msgList.size() == 0 ---" + getName() );
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return msgList.remove(0);
            }
        }
    }


    public class Consumer extends Thread {

        private Producer producer;

        public Consumer(String name, Producer producer) {
            super(name);
            this.producer = producer;
        }

        @Override public void run() {
            while (true) {
                Message msg = producer.waitMsg();
                System.out.println("Consumer " + getName() + " get a msg");
            }
        }

    }
}
