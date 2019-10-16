package com.atguigu.mythreadpooldemo;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 第四种或得多线程的方式 (线程池)
 * @author Ahuan
 * @version 1.0
 * @date 2019/10/16 13:57
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //自定义线程池
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        /**
         * 1. AbortPolicy(默认)：直接抛出RejectedExecutionException异常阻止系统正常运行
         *
         * 2. CallerRunsPolicy：“调用者运行”一种调节机制，该策略既不会抛弃任务，也不
         * 会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量。
         *
         * 3. DiscardOldestPolicy：抛弃队列中等待最久的任务，然后把当前任务加人队列中
         * 尝试再次提交当前任务。
         *
         * 4. DiscardPolicy：该策略默默地丢弃无法处理的任务，不予任何处理也不抛出异常。
         * 如果允许任务丢失，这是最好的一种策略。
         */

        //执行长期任务性能好，创建一个线程池，一池有N个固定的线程，有固定线程数的线程
        //ExecutorService executorService = Executors.newFixedThreadPool(5); //一池5线程

        //一个任务一个任务的执行，一池一线程
        //ExecutorService executorService = Executors.newSingleThreadExecutor(); //一池1线程

        //执行很多短期异步任务，线程池根据需要创建新线程，但在先前构建的线程可用时将重用它们。可扩容，遇强则强
        //ExecutorService executorService = Executors.newCachedThreadPool(); //一池n线程


        try {
            //模n个客户来银行办理业务,提交请求
            for (int i = 1; i <= 8; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务" + new Random().nextInt(10));
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }
}
