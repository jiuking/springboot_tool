package com.hjc.demo.springboot.init.runFK;

public class RunFKTest {
    public static void main(String[] args) {
        AbstractRunFk abstractRunFk = new ARunFK();
        AbstractRunFk abstractRunFk1 = new BRunFK();
        abstractRunFk.setNext(abstractRunFk1);
        abstractRunFk.support(null);
    }
}
