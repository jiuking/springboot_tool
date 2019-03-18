package com.hjc.demo.springboot.init.runFK;

public class BRunFK extends AbstractRunFk {

    @Override
    protected boolean isRunFK(RunFKEntity trouble) {
        System.out.println("BRunFK");
        return false;
    }
}
