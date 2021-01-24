package com.example.study.repository;

import com.component.LoginUserAuditorAware;
import com.config.JpaConfig;
import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.AdminUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

@Import({JpaConfig.class, LoginUserAuditorAware.class})
public class AdminUserRepositoryTest extends StudyApplicationTests {
    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    @DisplayName("AdminUser Create 테스트")
    public void create() {
        AdminUser adminUser = new AdminUser();

        adminUser.setAccount("AdminUser01");
        adminUser.setPassword("AdminUser01");
        adminUser.setStatus("REGISTERED");
        adminUser.setRole("PARTNER");

        // LoginUserAuditorAware 적용으로 자동 createdAt, createdBy 설정
        //adminUser.setCreatedAt(LocalDateTime.now());
        //adminUser.setCreatedBy("AdminServer");

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assertions.assertNotNull(newAdminUser);

        //update
        newAdminUser.setAccount("CHANGE");
        adminUserRepository.save(newAdminUser);
    }
}
