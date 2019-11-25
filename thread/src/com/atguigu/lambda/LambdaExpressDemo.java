package com.atguigu.lambda;

import sun.text.resources.cldr.or.FormatData_or;

import java.sql.SQLOutput;

interface Foo{
    //public void sayHello();

    public int add(int a,int b);

    default int dev(int a,int b){
        return a/b;
    }

    public static int mul(int a,int b){
        return a*b;
    }
}

/**
 * 2    Lambda表达式(前提是函数式接口,只有一个方法)
 *
 * 2.1 拷贝小括号,写死右箭头,落地大括号
 * 2.2 注解 @FunctionalInterface
 * 2.3 default方法
 * 2.4 static
 */
public class LambdaExpressDemo {
    public static void main(String[] args) {
        Foo  foo = (int a,int b) -> {
            System.out.println("-----come in add method");
            return a+b;
        };
        System.out.println("a*b=" + foo.add(3, 3));
        System.out.println("a/b=" +foo.dev(10,5));
        System.out.println("a*b=" + Foo.mul(3,4));
    }
}
