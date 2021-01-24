package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends StudyApplicationTests {
    @Autowired
    private OrderDetailRespository orderDetailRespository;
    @Test
    public void create() {
        OrderDetail orderDetail = new OrderDetail();

        // FK
        //orderDetail.setItemId(1L); // 상품
        //orderDetail.getOrderGroup(orderDetail); // Long -> OrderGroup
        // 생성자
        orderDetail.setStatus("WAITING");
            // : 도착일 현재일자 + 2
        orderDetail.setArrival_date(LocalDateTime.now().plusDays(2));
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(BigDecimal.valueOf(900000));
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("AdminServer");


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
