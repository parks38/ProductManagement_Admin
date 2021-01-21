package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
<<<<<<< HEAD
import java.math.BigDecimal;
=======
>>>>>>> 948b0c30b0bec2a2f6a6ab09e75eae6ed2d8383f
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity //order_detail
// 상호 참조 풀기
<<<<<<< HEAD
=======
@ToString(exclude = {"user", "item"})
>>>>>>> 948b0c30b0bec2a2f6a6ab09e75eae6ed2d8383f
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderAt;
<<<<<<< HEAD
    private String status;
    private LocalDateTime arrival_date;
    private Integer quantity;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
=======
    // N(order) : 1(user)
    @ManyToOne
    private User user;

    @ManyToOne
    private Item item;
>>>>>>> 948b0c30b0bec2a2f6a6ab09e75eae6ed2d8383f
}
