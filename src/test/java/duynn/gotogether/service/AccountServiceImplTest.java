package duynn.gotogether.service;

import duynn.gotogether.entity.Account;
import duynn.gotogether.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AccountServiceImplTest {
    @Autowired
    AccountServiceImpl accountService;

    @MockBean(name = "accountRepository")
    AccountRepository accountRepository;


    @Test
    @Rollback
    @Transactional
    void testDelete() {
        // Setup
        // Run the test
        final int result = accountService.delete(0L);
        when(accountService.delete(0L)).thenReturn(1);
        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    @Rollback
    @Transactional
    void testFindAll() throws Exception {
        // Setup
        final List<Account> expectedResult = List.of(new Account(0L, "username", "password"));
        when(accountRepository.findAll()).thenReturn(expectedResult);
        // Run the test
        final List<Account> result = accountService.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @Rollback
    @Transactional
    void testFindAll_ThrowsException() throws Exception {
        // Setup
        when(accountRepository.findAll()).thenReturn(new ArrayList<>());
        // Run the test
        assertThatThrownBy(() -> accountService.findAll()).isInstanceOf(Exception.class);
    }

    @Test
    @Rollback
    @Transactional
    void testFindById() throws Exception {
        // Setup
        final Account expectedResult = new Account(0L, "username", "password");
        when(accountRepository.findById(0L)).thenReturn(Optional.of(expectedResult));
        // Run the test
        final Account result = accountService.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_ThrowsException() throws Exception {
        // Setup
        when(accountRepository.findById(0L)).thenReturn(Optional.empty());
        // Run the test
        assertThatThrownBy(() -> accountService.findById(0L)).isInstanceOf(Exception.class);
    }

    @Test
    @Rollback
    @Transactional
    void testCreate() throws Exception {
        // Setup
        final Account account = new Account(0L, "username", "password");
        final Account expectedResult = new Account(0L, "username", "password");
        when(accountRepository.save(account)).thenReturn(expectedResult);

        // Run the test
        final Account result = accountService.create(account);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @Rollback
    @Transactional
    void testCreate_ThrowsException() throws Exception {
        // Setup
        final Account account = new Account(0L, "username", "password");
        when(accountRepository.findAccountByUsername(account.getUsername())).thenReturn(account);
        // Run the test
        assertThatThrownBy(() -> accountService.create(account)).isInstanceOf(Exception.class);
    }
    @Test
    @Rollback
    @Transactional
    void testUpdate() throws Exception {
        // Setup
        final Account account = new Account(0L, "username", "password");
        final Account expectedResult = new Account(0L, "username", "password");
        when(accountRepository.save(account)).thenReturn(expectedResult);
        // Run the test
        final Account result = accountService.update(account);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @Rollback
    @Transactional
    void testUpdate_ThrowsException() throws Exception {
        // Setup
        final Account account = new Account(0L, "username", "password");
        when(accountRepository.save(account)).thenReturn(null);
        // Run the test
        assertThatThrownBy(() -> accountService.update(account)).isInstanceOf(Exception.class);
    }
    @Test
    void testIsExist() {
        // Setup
        // Run the test
        final boolean result = accountService.isExist("username");
        when(accountRepository.findAccountByUsername("username")).thenReturn(null);
        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testLogin() {
        // Setup
        final Account data = new Account(0L, "username", "password");
        when(accountRepository.findAccountByUsername(data.getUsername())).thenReturn(data);
        // Run the test
        final boolean result = accountService.login(data);

        // Verify the results
        assertThat(result).isTrue();
    }

}