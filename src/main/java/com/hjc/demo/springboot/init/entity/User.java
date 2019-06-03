package com.hjc.demo.springboot.init.entity;

public class User {
    private String id;
    private String username;
//    private String plus;
//
//    public String getPlus() {
//        return plus;
//    }
//
//    public void setPlus(String plus) {
//        this.plus = plus;
//    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
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
//                ", plus='" + plus + '\'' +
                '}';
    }
}
