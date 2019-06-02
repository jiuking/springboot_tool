package com.hjc.demo.springboot.init.concurrent;

public class AlarmProducer extends Parent implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                alarmBlockingQueue.put(1);
                System.out.println(Thread.currentThread().getName()
                        + "警报生产者生产，目前总共有");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
