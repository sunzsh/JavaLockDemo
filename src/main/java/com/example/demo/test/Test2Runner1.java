package com.example.demo.test;

import com.example.demo.util.SynchronizedByKey;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.concurrent.CountDownLatch;

@AllArgsConstructor
@NoArgsConstructor
public class Test2Runner1 implements Runnable {
    static SynchronizedByKey synchronizedByKey = new SynchronizedByKey();

    private String id;
    private CountDownLatch countDownLatch;
    @Override
    public void run() {
        synchronizedByKey.exec(id, () -> {
//            System.out.println("开始");
            Test2.service();      //  <- sleep 1.5s
//            System.out.println("结束");
            countDownLatch.countDown();
        });
    }


}
