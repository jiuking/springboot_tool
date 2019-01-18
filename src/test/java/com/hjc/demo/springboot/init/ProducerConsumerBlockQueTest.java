package com.hjc.demo.springboot.init;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author : Administrator
 * @date : 2019/1/9 0009 15:02
 * @description : 生产者消费者阻塞队列
 */
public class ProducerConsumerBlockQueTest {
    public static void main(String[] args) {
        Resource2 resource = new Resource2();
        Producer2 producer1 = new Producer2(resource);
        Producer2 producer11 = new Producer2(resource);
        Consumer2 consumer1 = new Consumer2(resource);
        Consumer2 consumer11 = new Consumer2(resource);
        producer1.start();
//        producer11.start();
        consumer1.start();
        consumer11.start();
    }
}
class Resource2{
    private BlockingQueue blockingQueue = new LinkedBlockingQueue(10);

    public void add() {
        try {
            blockingQueue.put(1);
            System.out.println("生产者" + Thread.currentThread().getName()+ "生产一件资源," + "当前资源池有" + blockingQueue.size() + "个资源");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void remove() {
        try {
            blockingQueue.take();
            System.out.println("消费者" + Thread.currentThread().getName() +"消耗一件资源," + "当前资源池有" + blockingQueue.size() + "个资源");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer2 extends Thread {

    private Resource2 resource;

    public Producer2(Resource2 resource) {
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

class Consumer2 extends Thread {

    private Resource2 resource;

    public Consumer2(Resource2 resource) {
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

