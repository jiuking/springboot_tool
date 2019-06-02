package com.hjc.demo.springboot.init.repositoryBean;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import javax.persistence.OrderBy;
import java.io.Serializable;

public class SubRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, I> {

    SubRepositoryFactoryBean(Class repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        JpaRepositoryFactory jpaFac = new JpaRepositoryFactory(entityManager);
        jpaFac.addRepositoryProxyPostProcessor(new SecurecyPostProcessor());
        return jpaFac;
    }
}
