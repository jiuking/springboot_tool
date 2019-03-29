package com.hjc.demo.springboot.init;


import com.google.common.util.concurrent.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author : Administrator
 * @date : 2019/2/20 0020 14:32
 * @description : guava多线程
 */
public class GuavaThreadTest {
    public static void main(String[] args) throws InterruptedException {
        ListeningExecutorService executor = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        ListenableFuture<Integer> future1 = executor.submit(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int a = 1/0;
            return 100;
        });

        ListenableFuture<Integer> future2 = executor.submit(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 200;
        });

        List<ListenableFuture> list = new ArrayList<>();
        list.add(future1);
        list.add(future2);

        CountDownLatch countDownLatch = new CountDownLatch(2);
        System.out.println("Do something ...");
        for (ListenableFuture future: list) {
            Futures.addCallback(future, new FutureCallback<Integer>() {
                @Override
                public void onSuccess(Integer result) {
                    System.out.println("Future return value " + result);
                    countDownLatch.countDown();
                }

                @Override
                public void onFailure(Throwable t) {
                    System.out.println(t);
                    countDownLatch.countDown();
                }
            }, executor);
        }
//        semaphore.acquireUninterruptibly(2);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("... Main thread Done ...");
        Thread.sleep(10000);
        executor.shutdown();
    }
}
