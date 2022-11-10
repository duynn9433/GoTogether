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
class ClientServiceImplTest {

    @Autowired
    ClientServiceImpl clientService;

    Client clientData;

    @BeforeEach
    void setUp() {
        clientData = Client.builder()
                .avatar("avatar")
                .description("description")
                .role("role")
                .isActive(true)
                .account(Account.builder()
                        .username("duynn")
                        .password("password")
                        .build())
                .contactInfomation(ContactInfomation.builder()
                        .email("duynn@mail.com")
                        .phoneNumber("0966215413")
                        .build())
                .fullname(Fullname.builder()
                        .firstName("duy")
                        .lastName("nn")
                        .build())
                .address(Address.builder()
                        .city("HCM")
                        .district("Q1")
                        .province("province")
                        .detail("detail")
                        .build())
                .rate(10.0)
                .isInTrip(false)
                .build();
    }

    @Test
    @Transactional
    @Rollback
    void testFindAll() throws Exception {
        Client expected = clientService.create(clientData);
        List<Client> actual = clientService.findAll();
        assertNotNull(actual);

    }

    @Test
    @Transactional
    @Rollback
    void testFindById() throws Exception {
        Client expected = clientService.create(clientData);
        Client actual = clientService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @Rollback
    void testCreate() throws Exception {
        Client expected = clientService.create(clientData);
        Client actual = clientService.findById(expected.getId());
//        Client actual = new Client();
        assertEquals(expected, actual);

    }

    @Test
    @Transactional
    @Rollback
    void update() throws Exception {
        Client expected = clientService.create(clientData);
        expected.setAvatar("avatar2");
        clientService.update(expected);
        Client actual = clientService.findById(expected.getId());
//        Client actual = new Client();
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @Rollback
    void delete() throws Exception {
        Client expected = clientService.create(clientData);
        Client actual = clientService.findById(expected.getId());
        assertEquals(expected, actual);

        clientService.delete(expected.getId());
        try{
            actual = clientService.findById(expected.getId());
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Không tìm thấy dữ liệu");
        }
    }
}