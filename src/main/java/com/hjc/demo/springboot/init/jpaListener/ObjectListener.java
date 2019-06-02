package com.hjc.demo.springboot.init.jpaListener;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;

public class ObjectListener {
    @PostPersist
    public void postPersist(Object source){
        System.out.println("@PostPersist：" + source);
    }

    @PostLoad
    public void postLoad(Object object) {
        System.out.println("@PostLoad：" + object);
    }

    @PostUpdate
    public void postUpdate(Object object) {
        System.out.println("@PostLoad：" + object+"==");
    }

}
