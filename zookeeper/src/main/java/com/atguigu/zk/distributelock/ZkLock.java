package com.atguigu.zk.distributelock;

/**
 * @author Ahuan
 * @version 1.0
 * @date 2019/10/22 1:09
 */
public interface ZkLock {

    public void lock();

    public void unlock();

}
