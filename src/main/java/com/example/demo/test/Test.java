package com.example.demo.test;

import com.example.demo.util.SynchronizedByKey;


public class Test implements Runnable {
    static SynchronizedByKey synchronizedByKey = new SynchronizedByKey();

    @Override
    public void run() {
        synchronizedByKey.exec("001", () -> {
            System.out.println("开始");
            service();      //  <- sleep 1.5s
            System.out.println("结束");
        });

    }

    public static void main(String[] args) {
        for (int i = 0; i < 20000; i++) {
            new Thread(new Test()).start();
        }
    }

    private void service() {
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}
