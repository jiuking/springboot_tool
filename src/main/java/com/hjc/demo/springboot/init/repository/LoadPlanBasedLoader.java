package com.hjc.demo.springboot.init.repository;

import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.loader.plan.exec.internal.AbstractLoadPlanBasedLoader;
import org.hibernate.loader.plan.exec.spi.LoadQueryDetails;

import java.sql.ResultSet;

public class LoadPlanBasedLoader extends AbstractLoadPlanBasedLoader {

    private final SessionFactoryImplementor factory;

    protected final String asdf() {
        return "asdf";
    }

    public LoadPlanBasedLoader(
            SessionFactoryImplementor factory) {
        super(factory);
        this.factory = factory;
    }

    @Override
    protected LoadQueryDetails getStaticLoadQuery() {
        return null;
    }

    @Override
    protected int[] getNamedParameterLocs(String name) {
        return new int[0];
    }

    @Override
    protected void autoDiscoverTypes(ResultSet rs) {

    }
}
