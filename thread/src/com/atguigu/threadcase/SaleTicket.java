package com.atguigu.threadcase;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket {
    /*private int ticket = 30;
    public synchronized void sale(){
        if(ticket > 0) {
            System.out.println(Thread.currentThread().getName() + "\t卖出第" + ticket-- + "张\t 还剩下: " + ticket + "张");
        }
    }*/

    private int ticket = 30;
    Lock lock = new ReentrantLock();
    //lock取代synchronized
    public void sale() {
        lock.lock();
        try {
            if(ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "\t卖出第" + ticket-- + "张\t 还剩下: " + ticket + "张");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 题目：三个售票员     卖出      30张票
 * 目的：如何写出企业级的多线程程序
 * <p>
 * ** 高内聚，低耦合
 * <p>
 * 1 高内低耦的前提下，线程       操作          资源类
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 29; i++) {
                ticket.sale();
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 29; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 29; i++) {
                ticket.sale();
            }
        }, "A").start();


        //Thread(Runnable target, String name) Allocates a new Thread object.
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 39; i++) {
                    ticket.sale();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 39; i++) {
                    ticket.sale();
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 39; i++) {
                    ticket.sale();
                }
            }
        }, "C").start();*/

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
