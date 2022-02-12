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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

@RestController
@RequestMapping("/weak")
public class WeakController {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    Map<String, Object> weakHashMap = new WeakHashMap<>();
    List<Object> links = new ArrayList<>();

    @ResponseBody
    @RequestMapping(value = "/put/{orderId}")
    public Map<String, Object> put(@PathVariable("orderId") String orderId) {

        Object value = new Object();
        weakHashMap.put(orderId, value);
        links.add(value);

        logInfo();

        return ResponseMapBuilder.newBuilder().putSuccess().getResult();
    }

    @ResponseBody
    @RequestMapping(value = "/gc")
    public Map<String, Object> gc() {

        System.gc();

        logInfo();

        return ResponseMapBuilder.newBuilder().putSuccess().getResult();
    }


    @ResponseBody
    @RequestMapping(value = "/show")
    public Map<String, Object> show() {

        logInfo();

        return ResponseMapBuilder.newBuilder().putSuccess().getResult();
    }

    private void logInfo() {
        logger.debug("weakHashMap.size: {}", weakHashMap.size());
        logger.debug("links.size: {}\n", links.size());
    }


}
