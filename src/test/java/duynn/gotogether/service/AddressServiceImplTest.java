package duynn.gotogether.service;

import duynn.gotogether.entity.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressServiceImplTest {
    Address address;

    @Autowired
    AddressServiceImpl addressService;

    @BeforeEach
    void setUp() {
        address = Address.builder()
                .id(1L)
                .city("city1")
                .district("district1")
                .province("province1")
                .detail("detail1")
                .build();
    }

    @Test
    @Transactional
    @Rollback
    void findAll() throws Exception {
        Address createAddress = addressService.create(address);
        List<Address> addresses = addressService.findAll();
        assertNotNull(addresses);
    }

    @Test
    @Transactional
    @Rollback
    void findById() throws Exception {
        Address createAddress = addressService.create(address);
//        System.out.println("################## "+createAddress);
        Address address = addressService.findById(createAddress.getId());
        assertNotNull(address);
        assertEquals(createAddress, address);
    }

    @Test
    @Transactional
    @Rollback
    void create() throws Exception {
        Address address1 = addressService.create(address);
        Address address2 = addressService.findById(address1.getId());
        assertEquals(address1, address2);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}