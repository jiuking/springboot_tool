package com.hjc.demo.springboot.init;

import com.hjc.demo.springboot.init.dao.UserDao;
import com.hjc.demo.springboot.init.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.annotation.Target;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void queryAllUser() {
        List<User> user = userDao.findAllUser();
        for (User us : user) {
            System.out.println(us);
        }
    }

    @Test
    public void queryOneUser() {
        User user = userDao.findOne("1");
        System.out.println(user);
    }

    @Test
    public void createUser() {
        User user = new User();
        user.setId("6");
        user.setUsername("李四");
        userDao.createUser(user);
    }

    @Test
    public void deleteUser() {
        User user = new User();
        user.setId("3");
        userDao.deleteUser(user);
    }

    @Test
    public void updateUSer() {
        User user = new User();
        user.setUsername("张三");
        user.setId("6");
        userDao.updateUser(user);
    }
}
