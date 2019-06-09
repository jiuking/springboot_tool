package com.hjc.demo.springboot.init.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class Base implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer id;
    public Date createTime;

    @Override
    public String toString() {
        return "Base{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
