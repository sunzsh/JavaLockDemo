package com.example.demo.controller;

import com.example.demo.util.ResponseMapBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/locker")
public class LockerController {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    private Object obj = new Object();

    @ResponseBody
    @GetMapping(value = "/produce/{id}")
    public Map<String, Object> produce(@PathVariable("id") String id) throws InterruptedException {


        synchronized (obj) {
            logger.debug("[{}] 进入", id);
            obj.wait();
            logger.debug("[{}] 被唤醒", id);
        }

        return ResponseMapBuilder.newBuilder().putSuccess().getResult();
    }

    @ResponseBody
    @GetMapping(value = "/notify")
    public Map<String, Object> cmd_notify() {

        synchronized (obj) {
            obj.notify();
        }

        return ResponseMapBuilder.newBuilder().putSuccess().getResult();
    }

    @ResponseBody
    @GetMapping(value = "/notifyAll")
    public Map<String, Object> cmd_notifyAll() {

        synchronized (obj) {
            obj.notifyAll();
        }

        return ResponseMapBuilder.newBuilder().putSuccess().getResult();
    }



}
