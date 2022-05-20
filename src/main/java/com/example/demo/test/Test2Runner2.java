package com.example.demo.test;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.concurrent.CountDownLatch;

@AllArgsConstructor
@NoArgsConstructor
public class Test2Runner2 implements Runnable {
    private String id;

    private CountDownLatch countDownLatch;
    @Override
    public void run() {
        synchronized (id.intern()) {
//            System.out.println("开始");
            Test2.service();      //  <- sleep 1.5s
//            System.out.println("结束");
            countDownLatch.countDown();
        }
    }


}
