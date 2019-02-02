package com.hjc.demo.springboot.init;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : Administrator
 * @date : 2019/1/9 0009 14:35
 * @description : Lock加锁生产者消费者模式
 */
public class ProducerConsumerLockTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition producerCondition = lock.newCondition();
        Condition consumerCondition = lock.newCondition();
        Resource1 resource = new Resource1(lock, producerCondition, consumerCondition);
        Producer1 producer1 = new Producer1(resource);
        Producer1 producer11 = new Producer1(resource);
        Consumer1 consumer1 = new Consumer1(resource);
        Consumer1 consumer11 = new Consumer1(resource);
        producer1.start();
//        producer11.start();
        consumer1.start();
        consumer11.start();
    }
}

class Resource1 {

    private int num = 0;
    private int max = 10;
    private Lock lock;
    private Condition producerCondition;
    private Condition consumerCondition;

    public Resource1() {

    }

    public Resource1(Lock lock, Condition producerCondition, Condition consumerCondition) {
        this.lock = lock;
        this.producerCondition = producerCondition;
        this.consumerCondition = consumerCondition;
    }

    /**
     * 添加 生产
     */
    public void add() {
        lock.lock();
        try {
            if (num < max) {
                ++num;
                consumerCondition.signalAll();
                System.out.println(Thread.currentThread().getName() + "生产者生产数量：" + num);
            } else {
                try {
                    producerCondition.await();
                    System.out.println(Thread.currentThread().getName() + "等待消费者消费");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 移除 消费
     */
    public void remove() {
        try {
            lock.lock();
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "消费者消费" + num);
                --num;
                producerCondition.signalAll();
            } else {
                try {
                    consumerCondition.await();
                    System.out.println(Thread.currentThread().getName() + "消费者等待");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } finally {
            lock.unlock();
        }
    }
}

class Producer1 extends Thread {

    private Resource1 resource;

    public Producer1(Resource1 resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.add();
        }
    }
}

class Consumer1 extends Thread {

    private Resource1 resource;

    public Consumer1(Resource1 resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.remove();
        }
    }
}
