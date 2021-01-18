package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.jvm.hotspot.utilities.Assert;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired
    //Dependency Injection (DI) - 의존성 주입
    private UserRepository userRepository;
    public void create() {
        // 값을 object 로 설정
        User user = new User();
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");

        User newUser = userRepository.save(user);

        System.out.println(newUser);
    }

    public void read() {
        Optional<User> user = userRepository.findById(4L);
        // Optional<T> findById(ID id)

        // 존재할 경우에는
        user.ifPresent(selectUser -> {
            System.out.println("user: " + selectUser);
            System.out.println("email:" + selectUser.getEmail());
        });
    }

    public void update() {
        Optional<User> user = userRepository.findById(4L);

        // update 하기 위해서는 user이 존재해야함.
        user.ifPresent(selectUser -> {
            selectUser.setAccount("TestUser04");
            selectUser.setEmail("TestUser04@gmail.com");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });

    }

    @Test
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
