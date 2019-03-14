package com.hjc.demo.springboot.init.runFK;

public class ARunFK extends AbstractRunFk {

    @Override
    protected boolean resolve(Object trouble) {
        System.out.println("ARunFK");
        return false;
    }
}
