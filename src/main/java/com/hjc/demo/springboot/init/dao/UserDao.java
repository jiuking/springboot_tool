package com.hjc.demo.springboot.init.dao;

import com.hjc.demo.springboot.init.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//该注解可以有也可无 因Application加了mapperScan
@Mapper
public interface UserDao {
    /**
     * 新增用户
     * @param user
     */
    void createUser(User user);
    /**
     * 查询用户列表
     * @return
     */
    List<User> findAllUser();

    User findOne(String id);

    void deleteUser(User user);
}
