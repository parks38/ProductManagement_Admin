package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends StudyApplicationTests {
    @Autowired
    private OrderDetailRespository orderDetailRespository;
    @Test
    public void create() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderAt(LocalDateTime.now());

<<<<<<< HEAD
=======
        // which person bought : id = 1
        //orderDetail.setUser(2);
        //what item : 1L
        //orderDetail.setItemId(2L);

>>>>>>> 948b0c30b0bec2a2f6a6ab09e75eae6ed2d8383f
        OrderDetail newOrderDetail = orderDetailRespository.save(orderDetail);
        Assertions.assertNotNull(newOrderDetail);
    }
}
