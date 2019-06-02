package com.hjc.demo.springboot.init.concurrent;

import java.util.Objects;

public class HavingConsumer extends Parent implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("执行行为消费者");
            while (true) {
                Object obj= havingBlockingQueue.take();
                System.out.println(Thread.currentThread().getName()
                        + "行为消费者消费，目前总共有"+obj);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()
                + "行为消费者消费，目前总共有");
    }
}
