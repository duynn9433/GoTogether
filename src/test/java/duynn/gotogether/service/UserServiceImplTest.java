package duynn.gotogether.service;

import duynn.gotogether.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    User userData;

    @BeforeEach
    void setUp() {
        userData = User.builder()
                .account(Account.builder()
                        .username("duynn")
                        .password("123456")
                        .build())
                .address(Address.builder()
                        .city("HN")
                        .district("1")
                        .province("1")
                        .detail("1")
                        .build())
                .fullname(Fullname.builder()
                        .firstName("duynn")
                        .middleName("duynn")
                        .lastName("duynn")
                        .build())
                .contactInfomation(ContactInfomation.builder()
                        .email("duynn@mail.com")
                        .phoneNumber("123456789")
                        .build())
                .role("USER")
                .description("duynn")
                .isActive(true)
                .avatar("avatar")
                .build();

    }

    @Test
    @Transactional
    @Rollback
    void findAll() throws Exception {
        User expected = userService.create(userData);
        List<User> actual = userService.findAll();
        assertNotNull(actual);
    }

    @Test
    @Transactional
    @Rollback
    void findById() throws Exception {
        User expected = userService.create(userData);
        User actual = userService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @Rollback
    void create() throws Exception {
        User expected = userService.create(userData);
        assertEquals(expected, userData);
        System.out.println(userData);
    }

    @Test
    @Transactional
    @Rollback
    void update() throws Exception {
        User expected = userService.create(userData);
        expected.setAvatar("avatar2");
        userService.update(expected);
        User actual = userService.findById(expected.getId());

        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @Rollback
    void delete() throws Exception {
        User expected = userService.create(userData);
        User actual = userService.findById(expected.getId());
        assertEquals(expected, actual);

        userService.delete(expected.getId());
        try{
            actual = userService.findById(expected.getId());
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Không tìm thấy dữ liệu");
        }
    }
}