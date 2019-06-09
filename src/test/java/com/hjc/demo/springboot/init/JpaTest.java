package com.hjc.demo.springboot.init;

import com.hjc.demo.springboot.init.entity.User;
import com.hjc.demo.springboot.init.repository.UserRepository;
import com.hjc.demo.springboot.init.entity.UserEntity;
import org.apache.logging.slf4j.SLF4JLogger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTest {

    private static final Logger logger = LoggerFactory.getLogger(JpaTest.class);

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
        List<UserEntity> userEntityList = userRepository.findAll();
        for (UserEntity userEntity : userEntityList) {
            System.out.println(userEntity);
        }
    }

    @Test
    public void testOne() {
//        UserEntity userEntityList = userRepository.findById(2).get();
//        System.out.println(userEntityList);
    }

    @Test
    public void createOne() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(10);
        userEntity.setUsername("丽水");
        userRepository.save(userEntity);
    }

    @Test
    public void deleteOne() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(3);
        userRepository.delete(userEntity);
    }

    @Test
    public void updateOne() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(3);
        userEntity.setUsername("姓名");
        userEntity.setCreateTime(new Date());
        userRepository.save(userEntity);
    }

    @Test
    public void instUpdateAndDel() {
        createOne();
        deleteOne();
    }

    @Test
    public void threadName() throws InterruptedException {
//        testOne();
        instUpdateAndDel();
        new Thread(()->{
//            testOne();
            instUpdateAndDel();
        }).start();
        Thread.sleep(1000);
    }

    @Test
    public void testCustom() {
        logger.info("测试logger");
        userRepository.updateUser(1, "as0df");
    }

    @Test
    public void delCustom() {
        logger.info("删除logger");
        userRepository.deleteUser(1);
    }

    @Test
    public void test1() {
        userRepository.update1();
    }
}
