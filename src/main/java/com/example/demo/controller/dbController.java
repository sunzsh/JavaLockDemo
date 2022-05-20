package com.example.demo.controller;

import com.example.demo.service.IUserService;
import com.example.demo.util.ResponseMapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/db")
public class dbController {

    @Autowired
    IUserService userService;

    @ResponseBody
    @RequestMapping(value = "/users")
    public Map<String, Object> users() {
        return ResponseMapBuilder.newBuilder()
            .putSuccess()
            .put("list", userService.list())
            .getResult();
    }
}
