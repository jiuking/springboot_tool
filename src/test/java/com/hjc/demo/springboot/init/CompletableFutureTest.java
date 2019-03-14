package com.hjc.demo.springboot.init;

import java.util.concurrent.*;

public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        for (int i = 0; i <10 ; i++) {

            new Thread(() -> {
                // 模拟执行耗时任务
                System.out.println("task doing...");
                int k = 1/0;
                try {
                    BlockingQueue blockingQueue = null;
                    Thread.sleep(300);
                } catch (Exception e) {
//                e.printStackTrace();
//                    completableFuture.completeExceptionally(e);
//                    completableFuture.completeExceptionally(e);
                }
                // 告诉completableFuture任务已经完成
                completableFuture.complete("344");
            }).start();
        }
        // 获取任务结果，如果没有完成会一直阻塞等待
//        while (true) {
//            if (completableFuture.isCompletedExceptionally()){
//                System.out.println("出错");
//                break;
//            }
        try {
            String result = completableFuture.get();
            if (completableFuture.isDone() || !completableFuture.isCancelled() || !completableFuture.isCompletedExceptionally()) {
                //1000, TimeUnit.MILLISECONDS);
                System.out.println("计算结果:" + result);
            }
        } catch (Exception e) {
            System.out.println("出错");
        }
//                break;
//            }
        }
    }
