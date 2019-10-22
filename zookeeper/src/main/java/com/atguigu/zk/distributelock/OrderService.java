package com.atguigu.zk.distributelock;

/**
 * @author Ahuan
 * @version 1.0
 * @date 2019/10/22 0:45
 */
public class OrderService
{
    OrderNumGenerateUtil orderNumGenerateUtil = new OrderNumGenerateUtil();

    private ZkLock zkLock = new ZkDistributedLock();
    public void getOrderNumber()
    {
        zkLock.lock();
        try
        {
            System.out.println("叮当编号：   \t"+orderNumGenerateUtil.getOrderNumber());
        }finally {
            zkLock.unlock();
        }
    }
    public static void main(String[] args)
    {
        for (int i = 1; i <=20; i++) {
            new Thread(() -> {
                new OrderService().getOrderNumber();
            },String.valueOf(i)).start();
        }
    }



    /*private Lock lock = new ReentrantLock();

    public String getOrderNumber()
    {
        lock.lock();
        try
        {
            return orderNumGenerateUtil.getOrderNumber();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args)
    {
        OrderService orderService = new OrderService();

        for (int i = 1; i <=20; i++) {
            new Thread(() -> {
                String result = orderService.getOrderNumber();
                System.out.println(result);
            },String.valueOf(i)).start();
        }
    }*/

}
