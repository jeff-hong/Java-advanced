package com.atguigu.test;

class Test{
    public void test(){
        test();
    }
}

public class StackOverflowError {
    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }
}
