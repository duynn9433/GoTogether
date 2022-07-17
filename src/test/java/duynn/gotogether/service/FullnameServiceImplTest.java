package duynn.gotogether.service;

import duynn.gotogether.entity.Fullname;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FullnameServiceImplTest {

    @Autowired
    private FullnameServiceImpl fullnameService;

    Fullname fullnameData;

    @BeforeEach
    void setUp() {
        fullnameData = Fullname.builder()
                .firstName("duynn")
                .lastName("nguyen")
                .build();
    }

    @Test
    @Transactional
    @Rollback
    void findAll() throws Exception {
        Fullname expected = fullnameService.create(fullnameData);
        List<Fullname> actual = fullnameService.findAll();
        assertNotNull(actual);
    }

    @Test
    void findById() throws Exception {
        Fullname expected = fullnameService.create(fullnameData);
        Fullname actual = fullnameService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    void create() throws Exception {
        Fullname expected = fullnameService.create(fullnameData);
        Fullname actual = fullnameService.findById(expected.getId());
        assertEquals(expected, actual);
        System.out.println(actual);
    }
}