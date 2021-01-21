package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired
    //Dependency Injection (DI) - 의존성 주입
    private UserRepository userRepository;

    public void create() {

    }
    @Test
    @Transactional
    public void read() {

    }

    public void update() {
        Optional<User> user = userRepository.findById(4L);

        // update 하기 위해서는 user이 존재해야함.
        user.ifPresent(selectUser -> {
            selectUser.setAccount("TestUser01");
            selectUser.setEmail("TestUser01@gmail.com");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });

    }

    public void delete() {
        Optional<User> user = userRepository.findById(1L);

        Assertions.assertTrue(user.isPresent()); //true

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
            // void delete (T entity)
        });
        // 값이 삭제 되었는지 확인
        // 재 select 했을 때 존재하는지 안하는지로 delete 여부 확인
        Optional<User> deleteUser = userRepository.findById(1L);

        Assertions.assertFalse(deleteUser.isPresent()); //false

    }
}