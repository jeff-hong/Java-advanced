package com.atguigu.jvm;

public class T {
    public static void main(String[] args){
        //返回 Java 虚拟机试图使用的最大内存量。
        long maxMemory = Runtime.getRuntime().maxMemory() ;
        //返回 Java 虚拟机中的内存总量。
        long totalMemory = Runtime.getRuntime().totalMemory() ;
        System.out.println("TOTAL_MEMORY(-Xms) = " + totalMemory + "（字节）、" + (totalMemory / (double)1024 / 1024) + "MB");
        System.out.println("MAX_MEMORY(-Xmx) = " + maxMemory + "（字节）、" + (maxMemory / (double)1024 / 1024) + "MB");
        System.out.println("***********");
    }

}
