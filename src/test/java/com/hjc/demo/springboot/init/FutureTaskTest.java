package com.hjc.demo.springboot.init;

import java.util.concurrent.*;

/**
 * @author : Administrator
 * @date : 2019/1/7 0007 14:22
 * @description :
 */
public class FutureTaskTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> result = executor.submit(task);
        executor.shutdown();
        CompletableFuture completableFuture = new CompletableFuture();
        CompletionService completionService = new CompletionService() {
            @Override
            public Future submit(Callable task) {
                return null;
            }

            @Override
            public Future submit(Runnable task, Object result) {
                return null;
            }

            @Override
            public Future take() throws InterruptedException {
                return null;
            }

            @Override
            public Future poll() {
                return null;
            }

            @Override
            public Future poll(long timeout, TimeUnit unit) throws InterruptedException {
                return null;
            }
        };
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            while (true) {
                if (result.isDone() && !result.isCancelled()) {
                    int r = result.get();
                    System.out.println("task运行结果" + r);
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }
}

class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for (int i = 0; i < 100; i++)
            sum += i;
        return sum;
    }
}