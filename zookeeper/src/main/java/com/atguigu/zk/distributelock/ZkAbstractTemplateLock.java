package com.atguigu.zk.distributelock;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * @author Ahuan
 * @version 1.0
 * @date 2019/10/22 1:11
 */
public abstract class ZkAbstractTemplateLock implements ZkLock
{
    public static final String ZKSERVER = "192.168.128.88:2181";
    public static final int TIME_OUT = 45 * 1000;
    ZkClient zkClient = new ZkClient(ZKSERVER,TIME_OUT);

    protected String path = "/myzkLock2";
    protected CountDownLatch countDownLatch = null;


    @Override
    public void lock()
    {
        if(tryZkLock())
        {
            System.out.println(Thread.currentThread().getName()+"\t 抢占锁成功");
        }else{
            waitZkLock();

            lock();
        }
    }

    public abstract boolean tryZkLock();
    public abstract void waitZkLock();

    //模板设计模式，固定化的流程升级到父类定死规范，但是，具体落地实现方法下放给子类各自实现。



    @Override
    public void unlock()
    {
        if(zkClient != null)
        {
            zkClient.close();//等价于在zk服务器上执行quit退出命令
        }
        System.out.println(Thread.currentThread().getName()+"\t 释放锁成功");
        System.out.println();
        System.out.println();
    }
}
