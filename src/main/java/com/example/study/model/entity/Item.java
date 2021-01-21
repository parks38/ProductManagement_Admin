package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;

import javax.persistence.*;
<<<<<<< HEAD
import java.time.LocalDateTime;
=======
>>>>>>> 948b0c30b0bec2a2f6a6ab09e75eae6ed2d8383f
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
<<<<<<< HEAD
    private String status;
    private String title;
    private String content;
    private String name;
    private int price;
    private String brandName;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

=======
    private String name;
    private int price;
    private String content;

    // 1: (order detail) N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;
>>>>>>> 948b0c30b0bec2a2f6a6ab09e75eae6ed2d8383f
}
