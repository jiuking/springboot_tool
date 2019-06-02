package com.hjc.demo.springboot.init.concurrent;

import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) {
        Executors.newFixedThreadPool(1);
        ExecutorService executorService = new ThreadPoolExecutor(8, 16, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100));
        executorService.submit(new AlarmConsumer());
        executorService.submit(new AlarmProducer());
        executorService.submit(new HavingProducer());
        executorService.submit(new HavingConsumer());
        executorService.shutdown();
    }

}
