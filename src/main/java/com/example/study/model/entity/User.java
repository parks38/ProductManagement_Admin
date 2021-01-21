package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

// 기본 생성자 get, set method
@Data
// 모든 매개변수가 들어가는 user 생성
@AllArgsConstructor
// 기본 생성자
@NoArgsConstructor
@Entity
//@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column(name = "account")
    private String account;
<<<<<<< HEAD
    private String password;
    private String status;
    private String email;
    private String phoneNumber;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
=======
    private String email;
    private String phoneNumber;
>>>>>>> 948b0c30b0bec2a2f6a6ab09e75eae6ed2d8383f
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

<<<<<<< HEAD
=======
    // 1 : (order) N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderDetail> orderDetailList;



>>>>>>> 948b0c30b0bec2a2f6a6ab09e75eae6ed2d8383f
}
