package com.hjc.demo.springboot.init.concurrent;

public class HavingProducer extends Parent implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                havingBlockingQueue.put(1);
                System.out.println(Thread.currentThread().getName()
                        + "行为生产者生产，目前总共有");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
