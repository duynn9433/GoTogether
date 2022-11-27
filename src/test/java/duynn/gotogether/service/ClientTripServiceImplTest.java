package duynn.gotogether.service;

import duynn.gotogether.entity.ClientTrip;
import duynn.gotogether.repository.ClientTripRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientTripServiceImplTest {
    @Autowired
    ClientTripServiceImpl clientTripService;
    @Autowired
    ModelMapper modelMapper;
    @MockBean(name = "clientTripRepository")
    ClientTripRepository clientTripRepository;

    @Test
    void testCreate() {
        // Setup
        final ClientTrip clientTrip = ClientTrip.builder().build();
        when(clientTripRepository.save(any(ClientTrip.class)))
                .thenReturn(ClientTrip.builder().build());

        // Run the test
        final ClientTrip result = clientTripService.create(clientTrip);

        // Verify the results
    }

    @Test
    void testDelete() {
        // Setup
        when(clientTripRepository.deleteClientTripById(0L)).thenReturn(0);

        // Run the test
        final int result = clientTripService.delete(0L);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        // Configure ClientTripRepository.findById(...).
        final Optional<ClientTrip> clientTrip = Optional.of(ClientTrip.builder().build());
        when(clientTripRepository.findById(0L)).thenReturn(clientTrip);

        // Run the test
        final ClientTrip result = clientTripService.findById(0L);

        // Verify the results
    }

    @Test
    void testFindById_ClientTripRepositoryReturnsAbsent() {
        // Setup
        when(clientTripRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> clientTripService.findById(0L)).isInstanceOf(Exception.class);
    }

    @Test
    void testFindById_ThrowsException() {
        // Setup
        // Configure ClientTripRepository.findById(...).
        final Optional<ClientTrip> clientTrip = Optional.of(ClientTrip.builder().build());
        when(clientTripRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> clientTripService.findById(0L)).isInstanceOf(Exception.class);
    }

    @Test
    void testGetAcceptClientTripsByTripId() throws Exception {
        // Setup
        when(clientTripRepository.getAcceptClientTripsByTripId(0L))
                .thenReturn(List.of(ClientTrip.builder().build()));

        // Run the test
        final List<ClientTrip> result = clientTripService.getAcceptClientTripsByTripId(0L);

        // Verify the results
    }

    @Test
    void testGetAcceptClientTripsByTripId_ClientTripRepositoryReturnsNoItems() {
        // Setup
        when(clientTripRepository.getAcceptClientTripsByTripId(0L))
                .thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> clientTripService.getAcceptClientTripsByTripId(0L)).isInstanceOf(Exception.class);
    }

    @Test
    void testGetAcceptClientTripsByTripId_ThrowsException() {
        // Setup
        when(clientTripRepository.getAcceptClientTripsByTripId(0L))
                .thenReturn(new ArrayList<>());

        // Run the test
        assertThatThrownBy(() -> clientTripService.getAcceptClientTripsByTripId(0L)).isInstanceOf(Exception.class);
    }

    @Test
    void testGetClientTripsByTripIdAndIsCanceledIsFalse() throws Exception {
        // Setup
        when(clientTripRepository.getClientTripsByTripIdAndIsCanceledIsFalse(0L))
                .thenReturn(List.of(ClientTrip.builder().build()));

        // Run the test
        final List<ClientTrip> result = clientTripService.getClientTripsByTripIdAndIsCanceledIsFalse(0L);

        // Verify the results
    }

    @Test
    void testGetClientTripsByTripIdAndIsCanceledIsFalse_ClientTripRepositoryReturnsNoItems() {
        // Setup
        when(clientTripRepository.getClientTripsByTripIdAndIsCanceledIsFalse(0L))
                .thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> clientTripService.getClientTripsByTripIdAndIsCanceledIsFalse(0L))
                .isInstanceOf(Exception.class);
    }

    @Test
    void testGetClientTripsByTripIdAndIsCanceledIsFalse_ThrowsException() {
        // Setup
        when(clientTripRepository.getClientTripsByTripIdAndIsCanceledIsFalse(0L))
                .thenReturn(new ArrayList<>());

        // Run the test
        assertThatThrownBy(() -> clientTripService.getClientTripsByTripIdAndIsCanceledIsFalse(0L))
                .isInstanceOf(Exception.class);
    }

    @Test
    void testPassengerFinishTrip() {
        // Setup
        final ClientTrip clientTrip = ClientTrip.builder().build();
        when(clientTripRepository.save(any(ClientTrip.class)))
                .thenReturn(ClientTrip.builder().build());

        // Run the test
        final Integer result = clientTripService.passengerFinishTrip(clientTrip);

        // Verify the results
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testUpdate() {
        // Setup
        final ClientTrip clientTrip = ClientTrip.builder().build();
        when(clientTripRepository.save(any(ClientTrip.class)))
                .thenReturn(ClientTrip.builder().build());

        // Run the test
        final ClientTrip result = clientTripService.update(clientTrip);

        // Verify the results
    }
}