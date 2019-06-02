package com.hjc.demo.springboot.init.interceptor;

import org.hibernate.EmptyInterceptor;
import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/**
 * 增删改操作
 */
public class JpaInterceptor extends EmptyInterceptor implements StatementInspector {

//    private Map<String,Object>

    @Override
    public String inspect(String sql) {
        System.out.println("sql prepa 线程名：" + Thread.currentThread().getName());
        System.out.println(sql);
        System.out.println(this);
        System.out.println("=====================");
//        System.out.println("jpa sql:" + sql);
        return sql;
    }

    /*@Override
    public String onPrepareStatement(String sql) {
        System.out.println("sql prepa 线程名："+Thread.currentThread().getName());
        System.out.println("sql jpa:"+sql);
        return super.onPrepareStatement(sql);
    }*/


    /*@Override
    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        System.out.println("jpaa:"+entity);
        return super.onLoad(entity, id, state, propertyNames, types);
    }*/

    /**
     * 保存
     * @param entity
     * @param id
     * @param state
     * @param propertyNames
     * @param types
     * @return
     */
    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        System.out.println("保存:"+entity);
        System.out.println("sql 保存 线程名：" + Thread.currentThread().getName());
        return super.onSave(entity, id, state, propertyNames, types);
    }

    /**
     * 最大的关口，增删改都会路过
     * @param entities
     */
    @Override
    public void postFlush(Iterator entities) {
        System.out.println("最大管控");
        while (entities.hasNext()) {
            System.out.print(entities.next()+"\\n");
        }
        super.postFlush(entities);
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        System.out.println("更新："+entity);
        return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }

    /*@Override
    public int[] findDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        System.out.println("findDirty"+entity);
        return super.findDirty(entity, id, currentState, previousState, propertyNames, types);
    }*/

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        System.out.println("删除"+entity);
        super.onDelete(entity, id, state, propertyNames, types);
    }
}
