package duynn.gotogether.service;

import duynn.gotogether.entity.Client;
import duynn.gotogether.entity.Transport;
import duynn.gotogether.entity.place.Location;
import duynn.gotogether.repository.TransportRepository;
import duynn.gotogether.util.enumClass.TransportType;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransportServiceImplTest {

    @Autowired
    TransportServiceImpl transportService;
    @MockBean(name = "transportRepository")
    TransportRepository transportRepository;

    @Test
    void testCreate() {
        // Setup
        final Transport transport = new Transport(0L, "name", "licensePlate", "description", "image", TransportType.CAR,
                new Client(new Location(0L, 0.0, 0.0), 0.0, false, "fcmToken", List.of()));

        // Configure TransportRepository.save(...).
        when(transportRepository.save(transport)).thenReturn(transport);

        // Run the test
        final Transport result = transportService.create(transport);

        // Verify the results
        verify(transportRepository).save(transport);
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        // Configure TransportRepository.findById(...).
        final Optional<Transport> transport = Optional.of(
                new Transport(0L, "name", "licensePlate", "description", "image", TransportType.CAR,
                        new Client(new Location(0L, 0.0, 0.0), 0.0, false, "fcmToken", List.of())));
        when(transportRepository.findById(0L)).thenReturn(transport);

        // Run the test
        final Transport result = transportService.findById(0L);
        // Verify the results
        verify(transportRepository).findById(0L);
    }

    @Test
    void testFindById_ThrowsException() {
        // Setup
        // Configure TransportRepository.findById(...).
        final Optional<Transport> transport = Optional.of(
                new Transport(0L, "name", "licensePlate", "description", "image", TransportType.CAR,
                        new Client(new Location(0L, 0.0, 0.0), 0.0, false, "fcmToken", List.of())));
        when(transportRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> transportService.findById(0L)).isInstanceOf(Exception.class);
    }

    @Test
    void testGetTransportByUserId() throws Exception {
        // Setup
        // Configure TransportRepository.getTransportByOwnerId(...).
        final List<Transport> list = List.of(
                new Transport(0L, "name", "licensePlate", "description", "image", TransportType.CAR,
                        new Client(new Location(0L, 0.0, 0.0), 0.0, false, "fcmToken", List.of())));
        when(transportRepository.getTransportByOwnerId(0L)).thenReturn(list);

        // Run the test
        final List<Transport> result = transportService.getTransportByUserId(0L);

        // Verify the results
        verify(transportRepository).getTransportByOwnerId(0L);
        assertThat(result).isEqualTo(list);
    }

    @Test
    void testGetTransportByUserId_ThrowsException() {
        // Setup
        // Configure TransportRepository.getTransportByOwnerId(...).
        final List<Transport> list = List.of(
                new Transport(0L, "name", "licensePlate", "description", "image", TransportType.CAR,
                        new Client(new Location(0L, 0.0, 0.0), 0.0, false, "fcmToken", List.of())));
        when(transportRepository.getTransportByOwnerId(0L)).thenReturn(new ArrayList<>());

        // Run the test
        assertThatThrownBy(() -> transportService.getTransportByUserId(0L)).isInstanceOf(Exception.class);
    }
}