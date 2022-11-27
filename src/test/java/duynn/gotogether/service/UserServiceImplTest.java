package duynn.gotogether.service;

import duynn.gotogether.entity.*;
import duynn.gotogether.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;
    @MockBean(name = "userRepository")
    UserRepository userRepository;

    @Test
    @Transactional
    @Rollback
    void testCreate() throws Exception {
        // Setup
        final User user = new User(0L, "role", "avatar", "description", new Account(0L, "username", "password"),
                new Fullname(0L, "firstName", "lastName", "middleName", "nickName"),
                new ContactInfomation(0L, "phoneNumber", "email"),
                new Address(0L, "city", "district", "province", "detail"), false);
        final User expectedResult = new User(0L, "role", "avatar", "description",
                new Account(0L, "username", "password"),
                new Fullname(0L, "firstName", "lastName", "middleName", "nickName"),
                new ContactInfomation(0L, "phoneNumber", "email"),
                new Address(0L, "city", "district", "province", "detail"), false);
        when(userRepository.save(user)).thenReturn(expectedResult);
        // Run the test
        final User result = userService.create(user);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final User expectedResult = new User(0L, "role", "avatar", "description",
                new Account(0L, "username", "password"),
                new Fullname(0L, "firstName", "lastName", "middleName", "nickName"),
                new ContactInfomation(0L, "phoneNumber", "email"),
                new Address(0L, "city", "district", "province", "detail"), false);
        when(userRepository.findByIdAndIsActiveIsTrue(0L)).thenReturn(java.util.Optional.of(expectedResult));
        // Run the test
        final User result = userService.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_ThrowsException() throws Exception {
        when(userRepository.findByIdAndIsActiveIsTrue(0L)).thenReturn(java.util.Optional.empty());
        // Setup
        // Run the test
        assertThatThrownBy(() -> userService.findById(0L)).isInstanceOf(Exception.class);
    }
}