package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

<<<<<<< HEAD
=======
    // select * from user where account = ?;
    Optional<User> findByAccount(String account);

    Optional<User> findByEmail(String email);

    // select * from user where account = ? and email = ?
    Optional<User> findByAccountAndEmail(String account, String email);
>>>>>>> 948b0c30b0bec2a2f6a6ab09e75eae6ed2d8383f


}
