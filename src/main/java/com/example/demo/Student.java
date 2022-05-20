package com.example.demo;

import lombok.Data;

@Data
public class Student {
    private String name;
    private Address address;

    public String setAddr(Address addr) {
        this.address = addr;
        return this.name;
    }
}
