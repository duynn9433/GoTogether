package duynn.gotogether.service;

import duynn.gotogether.entity.Client;
import duynn.gotogether.entity.Transport;
import duynn.gotogether.entity.place.Location;
import duynn.gotogether.repository.ClientRepository;
import duynn.gotogether.repository.TransportRepository;
import duynn.gotogether.util.enumClass.TransportType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientServiceImplTest {

    @Autowired
    ClientServiceImpl clientService;

    @MockBean(name = "clientRepository")
    ClientRepository clientRepository;
    @MockBean(name = "transportRepository")
    TransportRepository transportRepository;

    @Test
    @Transactional
    @Rollback
    void testCreate() throws Exception {
        // Setup
        final Client client = new Client(new Location(0L, 0.0, 0.0), 0.0, false, "fcmToken",
                new ArrayList<>());
        final Client expectedResult = new Client(new Location(0L, 0.0, 0.0), 0.0, false, "fcmToken",
                new ArrayList<>());

        // Configure ClientRepository.save(...).
        when(clientRepository.save(client)).thenReturn(expectedResult);

        // Configure TransportRepository.save(...).
        final Transport transport = new Transport(0L, "name", "licensePlate", "description", "image", TransportType.CAR,
                new Client(new Location(0L, 0.0, 0.0), 0.0, false, "fcmToken", List.of()));
        transport.getOwner().setId(0L);
        when(transportRepository.save(transport)).thenReturn(transport);

        // Run the test
        final Client result = clientService.create(client);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @Transactional
    @Rollback
    void testCreate_ClientRepositoryReturnsNull() {
        // Setup
        final Client client = new Client(new Location(0L, 0.0, 0.0), 0.0, false, "fcmToken",
                List.of(new Transport(0L, "name", "licensePlate", "description", "image", TransportType.CAR, null)));
        when(clientRepository.save(new Client(new Location(0L, 0.0, 0.0), 0.0, false, "fcmToken",
                List.of(new Transport(0L, "name", "licensePlate", "description", "image", TransportType.CAR,
                        null))))).thenReturn(null);

        // Run the test
        assertThatThrownBy(() -> clientService.create(client)).isInstanceOf(Exception.class);
    }

    @Test
    @Transactional
    @Rollback
    void testDelete() {
        // Setup
        when(clientRepository.deleteClient(0L)).thenReturn(0);

        // Run the test
        final int result = clientService.delete(0L);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testFindAll() throws Exception {
        // Setup
        final List<Client> expectedResult = List.of(new Client(new Location(0L, 0.0, 0.0), 0.0, false, "fcmToken",
                List.of(new Transport(0L, "name", "licensePlate", "description", "image", TransportType.CAR, null))));

        // Configure ClientRepository.findByIsActiveIsTrue(...).
        final List<Client> clients = List.of(new Client(new Location(0L, 0.0, 0.0), 0.0, false, "fcmToken",
                List.of(new Transport(0L, "name", "licensePlate", "description", "image", TransportType.CAR, null))));
        when(clientRepository.findByIsActiveIsTrue()).thenReturn(clients);

        // Run the test
        final List<Client> result = clientService.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_ClientRepositoryReturnsNoItems() {
        // Setup
        when(clientRepository.findByIsActiveIsTrue()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> clientService.findAll()).isInstanceOf(Exception.class);
    }

    @Test
    void testFindAll_ThrowsException() {
        // Setup
        // Configure ClientRepository.findByIsActiveIsTrue(...).
        final List<Client> clients = List.of(new Client(new Location(0L, 0.0, 0.0), 0.0, false, "fcmToken",
                List.of(new Transport(0L, "name", "licensePlate", "description", "image", TransportType.CAR, null))));
        when(clientRepository.findByIsActiveIsTrue()).thenReturn(new ArrayList<>());

        // Run the test
        assertThatThrownBy(() -> clientService.findAll()).isInstanceOf(Exception.class);
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final Client expectedResult = new Client(new Location(0L, 0.0, 0.0), 0.0, false, "fcmToken",
                List.of(new Transport(0L, "name", "licensePlate", "description", "image", TransportType.CAR, null)));

        // Configure ClientRepository.findByIdAndIsActiveIsTrue(...).
        final Optional<Client> client = Optional.of(new Client(new Location(0L, 0.0, 0.0), 0.0, false, "fcmToken",
                List.of(new Transport(0L, "name", "licensePlate", "description", "image", TransportType.CAR, null))));
        when(clientRepository.findByIdAndIsActiveIsTrue(0L)).thenReturn(client);

        // Run the test
        final Client result = clientService.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_ClientRepositoryReturnsAbsent() {
        // Setup
        when(clientRepository.findByIdAndIsActiveIsTrue(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> clientService.findById(0L)).isInstanceOf(Exception.class);
    }

    @Test
    void testFindById_ThrowsException() {
        // Setup
        // Configure ClientRepository.findByIdAndIsActiveIsTrue(...).
        final Optional<Client> client = Optional.of(new Client(new Location(0L, 0.0, 0.0), 0.0, false, "fcmToken",
                List.of(new Transport(0L, "name", "licensePlate", "description", "image", TransportType.CAR, null))));
        when(clientRepository.findByIdAndIsActiveIsTrue(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> clientService.findById(0L)).isInstanceOf(Exception.class);
    }


    @Test
    @Transactional
    @Rollback
    void testUpdate() throws Exception {
        // Setup
        final Client client = new Client(new Location(0L, 0.0, 0.0), 0.0, false, "fcmToken",
                List.of(new Transport(0L, "name", "licensePlate", "description", "image", TransportType.CAR, null)));
        final Client expectedResult = new Client(new Location(0L, 0.0, 0.0), 0.0, false, "fcmToken",
                List.of(new Transport(0L, "name", "licensePlate", "description", "image", TransportType.CAR, null)));

        // Configure ClientRepository.save(...).

        when(clientRepository.save(client)).thenReturn(expectedResult);

        // Run the test
        final Client result = clientService.update(client);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    @Transactional
    @Rollback
    void testUpdateFcmToken() {
        // Setup
        when(clientRepository.updateFcmToken(0L, "token")).thenReturn(1);

        // Run the test
        final Integer result = clientService.updateFcmToken(0L, "token");

        // Verify the results
        assertThat(result).isEqualTo(1);
    }
}