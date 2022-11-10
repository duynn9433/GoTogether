package duynn.gotogether.service;

import duynn.gotogether.entity.*;
import duynn.gotogether.util.enumClass.TransportType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransportServiceImplTest {

    @Autowired
    TransportServiceImpl transportService;

    Transport transportData;
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
        transportData = Transport.builder()
                .name("Truck")
                .description("Truck description")
                .licensePlate("ABC123")
                .image("https://www.google.com/")
                .transportType(TransportType.CAR)
                .owner(clientData)
                .build();
    }

    @Test
    @Transactional
    @Rollback
    void findAll() throws Exception {
        Transport expected = transportService.create(transportData);
        List<Transport> actual = transportService.findAll();
        assertNotNull(actual);
    }

    @Test
    @Transactional
    @Rollback
    void findById() throws Exception {
        Transport expected = transportService.create(transportData);
        Transport actual = transportService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @Rollback
    void create() throws Exception {
        Transport expected = transportService.create(transportData);
        Transport actual = transportService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @Rollback
    void update() throws Exception {
        Transport expected = transportService.create(transportData);
        Transport actual = transportService.findById(expected.getId());
        assertEquals(expected, actual);
        expected.setName("Truck2");
        transportService.update(expected);
        actual = transportService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @Rollback
    void delete() throws Exception {
        Transport expected = transportService.create(transportData);
        Transport actual = transportService.findById(expected.getId());
        assertEquals(expected, actual);
        expected.setName("Truck2");
        transportService.delete(expected.getId());
        try{
            actual = transportService.findById(expected.getId());
            assertNull(actual);
        }catch (Exception e){
            assertEquals(e.getMessage(), "Không tìm thấy dữ liệu phương tiện");
        }
    }

    @Test
    void getTransportByUserId() {
        List<Transport> actual = transportService.getTransportByUserId(1L);
        assertNotNull(actual);
        System.out.println(actual);
        for (Transport transport : actual) {
            System.out.println(transport);
        }
    }
}