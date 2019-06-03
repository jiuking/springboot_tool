package com.hjc.demo.springboot.init.invoke;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.annotations.Table;
import org.springframework.stereotype.Component;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


@Component
@Aspect
public class MyTagAop {

    @Pointcut("@annotation(javax.persistence.Table)")
    public void tableAno() {

    }

    @Before(value = "tableAno()&& @annotation(table)")
    public void before(JoinPoint jp, Table table) {
        System.out.println("=+++++="+jp.getTarget().getClass());
    }
    @Pointcut("@annotation(com.hjc.demo.springboot.init.repository.MyAnnoatation)")
    public void tableAno1() {

    }

    @Before(value = "tableAno1()")
    public void before() {
        System.out.println("切面开始");
    }

    @Around(value = "tableAno1()")
    public Object  around(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("环绕通知的目标方法名为 ： " + proceedingJoinPoint.getSignature().getName());
        boolean flag = true;
                try {
                       Object object=proceedingJoinPoint.proceed();
                    after(proceedingJoinPoint);
                    return object;
                } catch (Throwable throwable) {
                    System.out.println("打印异常");
                    throwable.printStackTrace();
                    flag = false;
                }
            return  null;
    }

//    @After(value = "tableAno1()")
    public void after(JoinPoint jp) {
        System.out.println("asdf切面之后");
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        Annotation[] an = method.getDeclaredAnnotations();
        for (Annotation a : an) {
            System.out.println(a.annotationType());
            if (a instanceof Query) {
                System.out.println(((Query) a).value());
            }
        }
    }



}
