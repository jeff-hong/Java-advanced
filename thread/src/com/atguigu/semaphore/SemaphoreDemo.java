package com.atguigu.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号灯
 * 一个计数信号量。 在概念上，信号量维持一组许可证。
 * 如果有必要，每个acquire()都会阻塞，直到许可证可用，然后才能使用它。
 * 每个release()添加许可证，潜在地释放阻塞获取方。
 * 但是，没有使用实际的许可证对象; Semaphore只保留可用数量的计数，并相应地执行。
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3); //模拟3个停车位  ps:参数为1相当于变相的synchronized

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                boolean flag = false;
                try {
                    semaphore.acquire(); // 不见不散
                    //semaphore.tryAcquire();
                    flag = true;
                    System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
                    //暂停三秒线程
                    try { TimeUnit.SECONDS.sleep(new Random().nextInt(5)); } catch (InterruptedException e) { e.printStackTrace(); }
                    System.out.println(Thread.currentThread().getName() + "\t -----离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if (flag){
                        semaphore.release();
                    }
                }
            }, String.valueOf(i)).start();
        }
    }
}
