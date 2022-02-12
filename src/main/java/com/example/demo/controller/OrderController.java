package com.example.demo.controller;

import com.example.demo.util.ResponseMapBuilder;
import com.example.demo.util.SynchronizedByKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SynchronizedByKey synchronizedByKey;


    @ResponseBody
    @RequestMapping(value = "/process/{orderId}")
    public Map<String, Object> process(@PathVariable("orderId") String orderId) {

        synchronizedByKey.exec(orderId, () -> {
            logger.debug("[{}] 开始", orderId);
            service();      //  <- sleep 1.5s
            logger.debug("[{}] 结束", orderId);
        });

        return ResponseMapBuilder.newBuilder().putSuccess().getResult();
    }



    private void service() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
