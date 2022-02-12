package com.example.demo.util;


import java.util.HashMap;
import java.util.Map;

public class ResponseMapBuilder {
    public static final String KEY_RESULTCODE = "resultCode";
    public static final String KEY_RESULTMSG = "resultMsg";
    private Map<String, Object> result;

    public static ResponseMapBuilder emptyBuilder() {
        ResponseMapBuilder builder = new ResponseMapBuilder();
        return builder;
    }

    public static ResponseMapBuilder newBuilder() {
        ResponseMapBuilder builder = new ResponseMapBuilder();
        builder.result = new HashMap();
        return builder;
    }

    public static ResponseMapBuilder newBuilder(Map<String, Object> result) {
        ResponseMapBuilder builder = new ResponseMapBuilder();
        builder.result = result;
        return builder;
    }

    private ResponseMapBuilder() {
    }

    public Map<String, Object> getResult() {
        return this.result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public ResponseMapBuilder putSuccess() {
        if (this.result == null) {
            return this;
        } else {
            this.put(KEY_RESULTCODE, "0");
            this.put(KEY_RESULTMSG, "");
            return this;
        }
    }

    public ResponseMapBuilder put(String property, Object value) {
        this.result.put(property, value);
        return this;
    }
}