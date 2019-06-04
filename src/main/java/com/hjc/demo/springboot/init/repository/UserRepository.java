package com.hjc.demo.springboot.init.repository;

import com.hjc.demo.springboot.init.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    @Modifying
    @MyAnnoatation
    @Query(value = "update user_entity u set u.id = ?1 where u.username = ?2" ,nativeQuery = true)
    void updateUser(Integer id, String username);
}
