package com.example.demo;

import cn.hutool.core.util.NumberUtil;
import com.example.demo.util.BigNum;

import java.math.BigDecimal;

public class NumTest {
    public static void main(String[] args) {

        double v = BigNum.startOf(3).add(30).subtract(30).divide(3).doubleValue(2);


        System.out.println(v);
        System.out.println(0.05 + 0.01);
    }
}
