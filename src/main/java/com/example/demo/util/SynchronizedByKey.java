package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedByKey {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    Map<String, ReentrantLock> mutexCache = new ConcurrentHashMap<>();


    public void exec(String key, Runnable statement) {
        ReentrantLock mutex4Key = null;
        ReentrantLock mutexInCache;
        do {
            if (mutex4Key != null) {
                mutex4Key.unlock();
            }
            mutex4Key = mutexCache.computeIfAbsent(key, k -> new ReentrantLock());

            mutex4Key.lock();
            mutexInCache = mutexCache.get(key);
        /*
        1. mutexInCache == null
        2. mutex4Key != mutexInCache
         */
        } while (mutexInCache == null || mutex4Key != mutexInCache);

        try {
            statement.run();
        } finally {
            if (mutex4Key.getQueueLength() == 0) {
                mutexCache.remove(key);
            }
            mutex4Key.unlock();
        }
    }



}
