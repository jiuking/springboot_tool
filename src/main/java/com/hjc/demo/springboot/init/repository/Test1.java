package com.hjc.demo.springboot.init.repository;

import org.hibernate.engine.spi.SessionFactoryImplementor;

public class Test1 extends LoadPlanBasedLoader {
    public Test1(
            SessionFactoryImplementor factory) {
        super(factory);
    }
}
