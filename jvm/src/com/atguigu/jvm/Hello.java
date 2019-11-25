package com.atguigu.jvm;

import java.util.HashMap;

/**
 * @author Ahuan
 * @version 1.0
 * @date 2019/10/17 14:16
 */
public class Hello {
    public static void main(String[] args) {
        Hello hello = new Hello();
        HashMap<String, String> hashMap = new HashMap<>();
        System.out.println(hello.getClass().getClassLoader().getParent().getParent());
        System.out.println(hello.getClass().getClassLoader().getParent());
        System.out.println(hello.getClass().getClassLoader());
    }
}
