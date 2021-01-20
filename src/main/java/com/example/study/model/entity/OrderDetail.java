package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity //order_detail
// 상호 참조 풀기
@ToString(exclude = {"user", "item"})
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderAt;
    // N(order) : 1(user)
    @ManyToOne
    private User user;

    @ManyToOne
    private Item item;
}
