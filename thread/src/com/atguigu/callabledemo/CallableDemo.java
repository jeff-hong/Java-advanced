package com.atguigu.callabledemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*class MyThread implements Callable<String>{
    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "*****come in call method");
        return "Java";
    }
}*/

/**
 *Java
 * 多线程中,第3种获得多线程的方式
 *
 * 1    get方法一般请放在最后一行
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //FutureTask<String> futureTask = new FutureTask(new MyThread());
        FutureTask<String> futureTask = new FutureTask(() -> {
            System.out.println(Thread.currentThread().getName() + "*****come in call method");
            return "Java";
        });
        List<String> list = new ArrayList<>();
        new Thread(futureTask, "AAA").start();

        System.out.println("*****主线程: " + Thread.currentThread().getName());

        String result = futureTask.get(); //调用get方法得到call方法的返回值
        System.out.println(result);
    }
}
