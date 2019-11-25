package com.atguigu.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Ticket{
    int number = 30;
    public void sale(){
        System.out.println(Thread.currentThread().getName() + "\t  正在卖" + number-- + "票" + "\t  还剩下" + number + "票");
    }
}

/**
 * 线程池版卖票
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //ExecutorService executorService = Executors.newFixedThreadPool(3); //一池3线程
        //ExecutorService executorService = Executors.newCachedThreadPool(); //一池1线程
        ExecutorService executorService = Executors.newSingleThreadExecutor(); //一池n线程

        for (int i = 1; i <= 30; i++) {
            executorService.submit(() -> {
                ticket.sale();
            });
        }
        executorService.shutdown();
    }
}
