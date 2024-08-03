package com.example.demo.controller;

import com.example.demo.entity.MemberDto;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import com.example.demo.util.ResponseMapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/db")
public class dbController {

    @Autowired
    IUserService userService;

    @ResponseBody
    @GetMapping(value = "/users")
    public Map<String, Object> users() {
        return ResponseMapBuilder.newBuilder()
            .putSuccess()
            .put("list", userService.list())
            .getResult();
    }

    @GetMapping("/members")
    public Map<String, Object> members(MemberDto memberDto) {
        return ResponseMapBuilder.newBuilder()
            .putSuccess()
            .getResult();
    }
}
