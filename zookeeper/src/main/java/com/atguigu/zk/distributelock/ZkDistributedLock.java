package com.atguigu.zk.distributelock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * @author Ahuan
 * @version 1.0
 * @date 2019/10/22 8:28
 *
 * 1 引入pom
 *     <!-- zookeeper -->
 *          <dependency>
 *              <groupId>com.101tec</groupId>
 *              <artifactId>zkclient</artifactId>
 *              <version>0.10</version>
 *          </dependency>
 *          <dependency>
 *              <groupId>org.apache.zookeeper</groupId>
 *              <artifactId>zookeeper</artifactId>
 *              <version>3.4.9</version>
 *          </dependency>
 *
 *  2  启动zk服务器并保证防火墙关闭，client（win10）可以联通server（Linux上面的zk服务器）
 *  	2.1 ifconfig
 *  	2.2 ipconfig
 *
 *
 *  3  设计实现思想讲解
 *
 *     利用zk的znode节点唯一性，我们生成临时节点完成唯一性标识。
 *
 *  4   建立接口
 *  	public interface ZkLock
 */
public class ZkDistributedLock extends ZkAbstractTemplateLock {
    @Override
    public boolean tryZkLock() {
        try {
            zkClient.createEphemeral(path);
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void waitZkLock() {
        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {

            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }
        };

        zkClient.subscribeDataChanges(path, iZkDataListener);

        if (zkClient.exists(path)) {
            //不能干什么事情，必须等待path也即临时节点被删除后才能继续向下运行

            //？？？怎么让程序被卡在这里？？？
            countDownLatch = new CountDownLatch(1);

            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        zkClient.unsubscribeDataChanges(path, iZkDataListener);

    }
}
