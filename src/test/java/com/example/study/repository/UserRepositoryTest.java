package com.example.study.repository;

import com.component.LoginUserAuditorAware;
import com.config.JpaConfig;
import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Import({JpaConfig.class, LoginUserAuditorAware.class})
public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired
    //Dependency Injection (DI) - 의존성 주입
    private UserRepository userRepository;
    @Test
    public void create() {
        String account = "Test04";
        String password = "Test04";
        String status = "REGISTERED";
        String email = "Test04@gmail.com ";
        String phoneNumber = "010-1111-4444";
        LocalDateTime registeredAt = LocalDateTime.now();
//        LocalDateTime createdAt = LocalDateTime.now();
//        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
//        user.setCreatedAt(createdAt);
//        user.setCreatedBy(createdBy);

        //builder 사용
        User u = User.builder().account(account)
                        .password(password).status(status)
                        .email(email).build();

        User newUser = userRepository.save(user);

        Assertions.assertNotNull(newUser);


    }
    //@Test
    @Transactional
    public void read() {
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");


        //exception 처
        if(user != null) {
            // 주문 바구니 찾기
            user.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("---------- 주문묶음 --------------" );
                System.out.println("수령인: " + orderGroup.getRevName());
                System.out.println("수령지: " + orderGroup.getRevAddress());
                System.out.println("총금액: " + orderGroup.getTotalPrice());
                System.out.println("총수: " + orderGroup.getTotalQuantity());
                System.out.println("---------- 주문상 --------------" );
                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    // 파트너사 이름 - category
                    System.out.println("파트너사 이름: " + orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리: " + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문 상품: " + orderDetail.getItem().getName());
                    System.out.println("고객센터 번호: " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문의 상태: " + orderDetail.getStatus());
                    System.out.println("도착예정일자 : " + orderDetail.getArrival_date());


                });
            });
        }

        Assertions.assertNotNull(user);
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
