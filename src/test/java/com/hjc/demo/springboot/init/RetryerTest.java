package com.hjc.demo.springboot.init;

import com.github.rholder.retry.*;
import com.google.common.base.Predicate;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author : Administrator
 * @date : 2019/1/7 0007 11:24
 * @description :
 */
public class RetryerTest {
    public boolean uploadToOdps(final Map<String, Object> map) {
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfException().//设置异常重试源
                retryIfResult(new Predicate<Boolean>() {//设置自定义段元重试源，
            @Override
            public boolean apply(Boolean state) {//特别注意：这个apply返回true说明需要重试，与操作逻辑的语义要区分
                return true;
            }
        })
                .withStopStrategy(StopStrategies.stopAfterAttempt(5))//设置重试5次，同样可以设置重试超时时间
                .withWaitStrategy(WaitStrategies.fixedWait(100L, TimeUnit.MILLISECONDS)).build();//设置每次重试间隔

        try {
            //重试入口采用call方法，用的是java.util.concurrent.Callable<V>的call方法,所以执行是线程安全的
            boolean result = retryer.call(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    try {
                        //特别注意：返回false说明无需重试，返回true说明需要继续重试
                        return uploadToOdps(map);
                    } catch (Exception e) {
                        throw new Exception(e);
                    }
                }
            });

        } catch (ExecutionException e) {
        } catch (RetryException ex) {
        }
        return false;
    }
}
