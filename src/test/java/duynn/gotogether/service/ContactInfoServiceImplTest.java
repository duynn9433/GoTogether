package duynn.gotogether.service;

import duynn.gotogether.entity.ContactInfomation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactInfoServiceImplTest {

    @Autowired
    private ContactInfoServiceImpl contactInfoService;

    ContactInfomation contactInfomationData;

    @BeforeEach
    void setUp() {
        contactInfomationData = ContactInfomation.builder()
                .email("duynn@mail.com")
                .phoneNumber("0966215413")
                .build();
    }

    @Test
    @Transactional
    @Rollback
    void findAll() throws Exception {
        ContactInfomation expected = contactInfoService.create(contactInfomationData);
        List<ContactInfomation> actual = contactInfoService.findAll();
        assertNotNull(actual);
    }

    @Test
    @Transactional
    @Rollback
    void findById() throws Exception {
        ContactInfomation expected = contactInfoService.create(contactInfomationData);
        ContactInfomation actual = contactInfoService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @Rollback
    void create() throws Exception {
        ContactInfomation expected = contactInfoService.create(contactInfomationData);
        ContactInfomation actual = contactInfoService.findById(expected.getId());
        assertEquals(expected, actual);
    }
}