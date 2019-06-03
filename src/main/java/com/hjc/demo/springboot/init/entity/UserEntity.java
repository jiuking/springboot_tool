package com.hjc.demo.springboot.init.entity;

import com.hjc.demo.springboot.init.jpaListener.ObjectListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_entity")
@EntityListeners(value = {ObjectListener.class})
public class UserEntity extends Base{
    @Id
    private Integer id;
    private String username;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
