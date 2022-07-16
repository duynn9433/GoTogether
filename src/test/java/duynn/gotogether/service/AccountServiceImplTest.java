package duynn.gotogether.service;

import duynn.gotogether.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceImplTest {
    @Autowired
    AccountServiceImpl accountService;

    Account account;
    @BeforeEach
    void setUp() {
        account = Account.builder()
                .id(1L)
                .username("duynn")
                .password("123456")
                .build();
    }

    @Test
    void findAll() throws Exception {
        List<Account> accounts = accountService.findAll();
        assertNotNull(accounts);
        assertEquals(account, accounts.get(0));
    }

    @Test
    void findById() throws Exception {
        Account account = accountService.findById(1L);
        assertNotNull(account);
        assertEquals(this.account, account);
    }

    @Test
    @Transactional
    @Rollback
    void create() throws Exception {

        try{
            accountService.create(account);
        }catch (Exception e){
            assertTrue(e.getMessage().contains("Username đã tồn tại"));
        }

//        Account account1 = accountService.findById(account.getId());
//        assertEquals(account.getUsername(), account1.getUsername());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}