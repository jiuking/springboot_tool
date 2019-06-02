package com.hjc.demo.springboot.init.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Parent {

    //创建一个阻塞队列
    protected static final BlockingQueue alarmBlockingQueue = new ArrayBlockingQueue<>(1024);
    protected static final BlockingQueue havingBlockingQueue = new ArrayBlockingQueue(1024);


}
