package duynn.gotogether.service;

import duynn.gotogether.entity.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PositionServiceImplTest {

    @Autowired
    PositionServiceImpl positionService;

    Position position;

    @BeforeEach
    void setUp() {
        position  = Position.builder()
                .latitude(10.0)
                .longitude(10.0)
                .build();
    }

    @Test
    @Transactional
    @Rollback
    void findAll() throws Exception {
        List<Position> positions = positionService.findAll();
        assertNotNull(positions);
    }

    @Test
    @Transactional
    @Rollback
    void findById() throws Exception {
        Position expected = positionService.create(position);
        Position actual = positionService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @Rollback
    void create() throws Exception {
        Position expected = positionService.create(position);
        Position actual = positionService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @Rollback
    void update() throws Exception {
        Position expected = positionService.create(position);
        expected.setLatitude(20.0);
        expected.setLongitude(20.0);
        positionService.update(expected);

        Position actual = positionService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @Rollback
    void delete() throws Exception {
        Position expected = positionService.create(position);
        Position actual = positionService.findById(expected.getId());
        assertEquals(expected, actual);
        positionService.delete(expected.getId());
        try {
            actual = positionService.findById(expected.getId());
            assertNull(actual);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Không tìm thấy dữ liệu vị trí");
        }
    }
}