package com.hjc.demo.springboot.init.repositoryBean;

import org.aopalliance.intercept.Invocation;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.support.RepositoryProxyPostProcessor;

public class SecurecyPostProcessor implements RepositoryProxyPostProcessor {
    @Override
    public void postProcess(ProxyFactory factory, RepositoryInformation repositoryInformation) {

    }
    /*static enum SecurecyAdvice implements MethodInterceptor {
        instance;
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            System.out.println("将要执行方法："+ Invocation.getMethod().getName());
            Object obj=Invocation.proceed();
            System.out.println(Invocation.getMethod().getName()+"已经被执行");
            return null;
        }

    }*/

}
