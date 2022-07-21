package duynn.gotogether.service;

import duynn.gotogether.entity.*;
import duynn.gotogether.util.enumClass.TransportType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TripServiceImplTest {

    @Autowired
    TripServiceImpl tripService;

    Trip trip;
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
                .position(Position.builder()
                        .latitude(10.0)
                        .longitude(10.0)
                        .build())
                .rate(10.0)
                .isInTrip(false)
                .build();
        trip = Trip.builder()
                .startPosition(Position.builder()
                        .latitude(10.0)
                        .longitude(10.0)
                        .build())
                .endPosition(Position.builder()
                        .latitude(20.0)
                        .longitude(20.0)
                        .build())
                .startTime(new Date())
                .totalSeat(10)
                .emptySeat(5)
                .pricePerKm(100.0)
                .description("description")
                .distancePlus(10.0)
                .transport(Transport.builder()
                        .name("name")
                        .licensePlate("licensePlate")
                        .image("image")
                        .description("description")
                        .transportType(TransportType.CAR)
                        .owner(clientData)
                        .build())
                .Driver(clientData)
                .isFinished(false)
                .isCanceled(false)
                .isStarted(false)
                .description("description")
                .build();
    }

    @Test
    @Transactional
    @Rollback
    void findAll() {
    }

    @Test
    @Transactional
    @Rollback
    void findById() throws Exception {
        Trip expected = tripService.create(trip);
        Trip actual = tripService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @Rollback
    void create() throws Exception {
        Trip expected = tripService.create(trip);
        Trip actual = tripService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @Rollback
    void update() throws Exception {
        Trip expected = tripService.create(trip);
        Trip actual = tripService.findById(expected.getId());
        assertEquals(expected, actual);
        expected.setDescription("description2");
        tripService.update(expected);
        actual = tripService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @Rollback
    void delete() {
    }
}