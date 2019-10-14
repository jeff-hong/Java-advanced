package com.atguigu.readwritelockdemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    private volatile Map<String, String> map = new HashMap<>();

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock lock = new ReentrantLock();
    public void put(String key, String value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 写入开始");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            String result = null;
            System.out.println(Thread.currentThread().getName() + "\t 读取开始");
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取结束:result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().lock();
        }
    }

    /*private Lock lock = new ReentrantLock();
    public void put(String key, String value) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 写入开始");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void get(String key) {
        lock.lock();
        try {
            String result = null;
            System.out.println(Thread.currentThread().getName() + "\t 读取开始");
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取结束:result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }*/
}

/**
 * 多个线程同时读一个资源类没有任何问题,所以为了满足并发量,读取共享资源应该可以同时进行
 * 但是
 * 如果有一个线程想去写共享资源,就不应该再有其他线程可以对该线程进行读或写
 * 小总结：
 *           读-读能共存
 *           读-写不能共存
 *           写-写不能共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache cache = new MyCache();

        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new Thread(() -> {
                cache.put(finalI + "", finalI + "");
            }, String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new Thread(() -> {
                cache.get(finalI + "");
            }, String.valueOf(i)).start();
        }

    }
}

