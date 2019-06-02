package com.hjc.demo.springboot.init.concurrent;

public class AlarmConsumer extends Parent implements Runnable {
    private Object object;
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            try {

                alarmBlockingQueue.take();
                object.toString();
                System.out.println(Thread.currentThread().getName()
                        + "警报消费者消费，目前总共有");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                //若这儿没有捕获Exception异常，线程也不会抛出其他异常打印信息。线程继续执行。没有任何错误提示。神奇。
                // 所有最好还是添加Exception异常捕获最好

                e.printStackTrace();
            }
        }
    }
}
