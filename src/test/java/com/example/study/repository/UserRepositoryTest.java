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
<<<<<<< HEAD

=======
        // 값을 object 로 설정
        User user = new User();
        user.setAccount("TestUser04");
        user.setEmail("TestUser04@gmail.com");
        user.setPhoneNumber("010-1111-4444");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser4");

        User newUser = userRepository.save(user);

        System.out.println(newUser);
>>>>>>> 948b0c30b0bec2a2f6a6ab09e75eae6ed2d8383f
    }
    @Test
    @Transactional
    public void read() {
<<<<<<< HEAD

=======
        Optional<User> user = userRepository.findByAccount("TestUser03");
        // Optional<T> findById(ID id)

        //OrderDetailList item find
        user.ifPresent(selectUser -> {
            selectUser.getOrderDetailList().stream().forEach(detail -> {
                Item item = detail.getItem();
                System.out.println(item);
            });
        });
>>>>>>> 948b0c30b0bec2a2f6a6ab09e75eae6ed2d8383f
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
