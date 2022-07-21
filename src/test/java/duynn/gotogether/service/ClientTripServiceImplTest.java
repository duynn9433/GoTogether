package duynn.gotogether.service;

import duynn.gotogether.entity.ClientTrip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientTripServiceImplTest {
    @Autowired
    ClientTripServiceImpl clientTripService;

    ClientTrip clientTripData;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Transactional
    @Rollback
    void findAll() {
    }

    @Test
    @Transactional
    @Rollback
    void findById() {
    }

    @Test
    @Transactional
    @Rollback
    void create() {
    }

    @Test
    @Transactional
    @Rollback
    void update() {
    }

    @Test
    @Transactional
    @Rollback
    void delete() {
    }
}