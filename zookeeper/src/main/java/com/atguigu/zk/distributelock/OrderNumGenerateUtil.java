package com.atguigu.zk.distributelock;

/**
 * @author Ahuan
 * @version 1.0
 * @date 2019/10/22 0:44
 */
public class OrderNumGenerateUtil
{
    private static int number = 0;

    public String getOrderNumber()
    {
        return ""+(++number);
    }
}
