package com.example.demo.test;


import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class Test2 {
    public static String[] buildIds(int count) {
        String[] ids = new String[count];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = UUID.randomUUID().toString();
        }
        return ids;
    }

    public static void service() {
//        try {
//            Thread.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) throws InterruptedException {
        String[] ids = buildIds(500000);
        test1(ids);
        test2(ids);
    }

    public static void test1(String[] ids) throws InterruptedException {
        long start = System.nanoTime();
        CountDownLatch countDownLatch = new CountDownLatch(ids.length);
        for (String id : ids) {
            new Thread(new Test2Runner1(id, countDownLatch)).start();
        }
        countDownLatch.await();
        long end = System.nanoTime();
        System.out.println("test1全部完成:" + (end - start) / 1000000);
    }


    public static void test2(String[] ids) throws InterruptedException {
        long start = System.nanoTime();
        CountDownLatch countDownLatch = new CountDownLatch(ids.length);
        for (String id : ids) {
            new Thread(new Test2Runner2(id, countDownLatch)).start();
        }
        countDownLatch.await();
        long end = System.nanoTime();
        System.out.println("test2全部完成:" + (end - start) / 1000000);
    }
}
