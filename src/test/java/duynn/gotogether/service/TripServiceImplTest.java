package duynn.gotogether.service;

import com.google.gson.Gson;
import duynn.gotogether.entity.*;
import duynn.gotogether.entity.place.Location;
import duynn.gotogether.repository.ClientRepository;
import duynn.gotogether.repository.TripRepository;
import duynn.gotogether.util.enumClass.TransportType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TripServiceImplTest {

    @Autowired
    TripServiceImpl tripService;
    @MockBean(name = "tripRepository")
    TripRepository tripRepository;
    @MockBean(name = "clientRepository")
    ClientRepository clientRepository;

    @Test
    @Transactional
    @Rollback
    void testCancelTrip() throws Exception {
        // Setup
        final Trip trip = Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build();
        when(tripRepository.cancelTripById(0L, true)).thenReturn(1);
        when(clientRepository.setIsInTripById(0L, false)).thenReturn(1);

        // Run the test
        final boolean result = tripService.cancelTrip(trip);

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    @Transactional
    @Rollback
    void testCancelTrip_ThrowsException() throws Exception {
        // Setup
        final Trip trip = Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build();
        when(tripRepository.cancelTripById(0L, true)).thenReturn(0);
        when(clientRepository.setIsInTripById(0L, false)).thenReturn(0);

        // Run the test
        assertThatThrownBy(() -> tripService.cancelTrip(trip)).isInstanceOf(Exception.class);
    }

    @Test
    @Transactional
    @Rollback
    void testCreate() throws Exception {
        // Setup
        final Trip trip = Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build();
        final Trip expectedResult = Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build();

        // Configure TripRepository.save(...).
        when(tripRepository.save(trip)).thenReturn(expectedResult);

        // Run the test
        final Trip result = tripService.create(trip);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @Transactional
    @Rollback
    void testDelete() throws Exception {
        // Setup
        when(tripRepository.deleteTripById(0L)).thenReturn(0);

        // Run the test
        final int result = tripService.delete(0L);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final Trip expectedResult = Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build();

        // Configure TripRepository.findById(...).
        when(tripRepository.findById(0L)).thenReturn(Optional.ofNullable(expectedResult));

        // Run the test
        final Trip result = tripService.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_ThrowsException() throws Exception {
        // Setup
        when(tripRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> tripService.findById(0L)).isInstanceOf(Exception.class);
    }

    @Test
    @Transactional
    @Rollback
    void testFinishTrip() throws Exception {
        // Setup
        final Trip trip = Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build();
        when(tripRepository.finishTripById(0L, true)).thenReturn(1);
        when(clientRepository.setIsInTripById(0L, false)).thenReturn(1);

        // Run the test
        final boolean result = tripService.finishTrip(trip);

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    @Transactional
    @Rollback
    void testFinishTrip_ThrowsException() throws Exception {
        // Setup
        final Trip trip = Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build();
        when(tripRepository.finishTripById(0L, true)).thenReturn(0);
        when(clientRepository.setIsInTripById(0L, false)).thenReturn(0);

        // Run the test
        assertThatThrownBy(() -> tripService.finishTrip(trip)).isInstanceOf(Exception.class);
    }

    @Test
    void testGetAcceptedTripNotFinished() throws Exception {
        // Setup
        final Trip expectedResult = Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build();

        // Configure TripRepository.getAcceptedTripByClientId(...).
        final Optional<Trip> trip = Optional.of(Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build());
        when(tripRepository.getAcceptedTripByClientId(0L)).thenReturn(trip);

        // Run the test
        final Trip result = tripService.getAcceptedTripNotFinished(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAcceptedTripNotFinished_ThrowsException() throws Exception {
        // Setup
        // Configure TripRepository.getAcceptedTripByClientId(...).
        final Optional<Trip> trip = Optional.of(Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build());
        when(tripRepository.getAcceptedTripByClientId(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> tripService.getAcceptedTripNotFinished(0L)).isInstanceOf(Exception.class);
    }

    @Test
    void testGetTripByStartEndLocation() throws Exception {
        // Setup
        final Location startLocation = new Location(0L, 0.0, 0.0);
        final Location endLocation = new Location(0L, 0.0, 0.0);
        final List<Trip> expectedResult = List.of(Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build());

        // Configure TripRepository.getTripByStartEndLocation(...).
        when(tripRepository.getTripByStartEndLocation(0.0, 0.0, 0.0, 0.0)).thenReturn(expectedResult);

        // Run the test
//        final List<Trip> result = tripService.getTripByStartEndLocation(startLocation, endLocation);

        // Verify the results
//        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetTripByStartEndLocation_ThrowsException() throws Exception {
        // Setup
        final Location startLocation = new Location(0L, 0.0, 0.0);
        final Location endLocation = new Location(0L, 0.0, 0.0);

        // Configure TripRepository.getTripByStartEndLocation(...).
        when(tripRepository.getTripByStartEndLocation(0.0, 0.0, 0.0, 0.0)).thenReturn(new ArrayList<>());

        // Run the test
//        assertThatThrownBy(() -> tripService.getTripByStartEndLocation(startLocation, endLocation)).isInstanceOf(
//                Exception.class);
    }

    @Test
    void testGetTripNotFinishedByDriverId() throws Exception {
        // Setup
        final Trip expectedResult = Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build();

        // Configure TripRepository.getTripNotFinishedByDriverId(...).
        when(tripRepository.getTripNotFinishedByDriverId(0L)).thenReturn(Optional.ofNullable(expectedResult));

        // Run the test
        final Trip result = tripService.getTripNotFinishedByDriverId(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetTripNotFinishedByDriverId_ThrowsException() throws Exception {
        // Setup
        // Configure TripRepository.getTripNotFinishedByDriverId(...).
        when(tripRepository.getTripNotFinishedByDriverId(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> tripService.getTripNotFinishedByDriverId(0L)).isInstanceOf(Exception.class);
    }

    @Test
    void testGetWaitingTripByPassengerId() throws Exception {
        // Setup
        final List<Trip> expectedResult = List.of(Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build());

        // Configure TripRepository.getWaitingTripByPassengerId(...).
        final List<Trip> trips = List.of(Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build());
        when(tripRepository.getWaitingTripByPassengerId(0L)).thenReturn(trips);

        // Run the test
        final List<Trip> result = tripService.getWaitingTripByPassengerId(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetWaitingTripByPassengerId_ThrowsException() throws Exception {
        // Setup
        // Configure TripRepository.getWaitingTripByPassengerId(...).
        final List<Trip> trips = List.of(Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build());
        when(tripRepository.getWaitingTripByPassengerId(0L)).thenReturn(new ArrayList<>());

        // Run the test
        assertThatThrownBy(() -> tripService.getWaitingTripByPassengerId(0L)).isInstanceOf(Exception.class);
    }

    @Test
    @Transactional
    @Rollback
    void testPublishTrip() throws Exception {
        // Setup
        final Trip Trip = duynn.gotogether.entity.Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build();
        final duynn.gotogether.entity.Trip expectedResult = duynn.gotogether.entity.Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build();

        // Configure TripRepository.save(...).
        final duynn.gotogether.entity.Trip trip = duynn.gotogether.entity.Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build();
        when(tripRepository.save(trip)).thenReturn(expectedResult);

        when(clientRepository.setIsInTripById(0L, true)).thenReturn(1);

        // Run the test
        final duynn.gotogether.entity.Trip result = tripService.publishTrip(Trip);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @Transactional
    @Rollback
    void testPublishTrip_ThrowsException() throws Exception {
        // Setup
        final Trip Trip = duynn.gotogether.entity.Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build();

        // Configure TripRepository.save(...).
        final duynn.gotogether.entity.Trip trip = duynn.gotogether.entity.Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build();
        when(tripRepository.save(duynn.gotogether.entity.Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build())).thenReturn(trip);

        when(clientRepository.setIsInTripById(0L, true)).thenReturn(0);

        // Run the test
        assertThatThrownBy(() -> tripService.publishTrip(Trip)).isInstanceOf(Exception.class);
    }

    @Test
    void testSearchTrip() throws Exception {
        // Setup
        final Location startLocation = new Location(0L, 0.0, 0.0);
        final Location endLocation = new Location(0L, 0.0, 0.0);
        final Calendar startTime = Calendar.getInstance(Locale.US);
        final List<Trip> expectedResult = List.of(Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build());

        // Configure TripRepository.getTripByStartEndLocation(...).
        final List<Trip> trips = List.of(Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build());
        when(tripRepository.getTripByStartEndLocation(0.0, 0.0, 0.0, 0.0)).thenReturn(trips);

        // Run the test
//        final List<Trip> result = tripService.searchTrip(startLocation, endLocation, startTime, 0);
//
//        // Verify the results
//        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSearchTrip_ThrowsException() throws Exception {
        // Setup
        final Location startLocation = new Location(0L, 0.0, 0.0);
        final Location endLocation = new Location(0L, 0.0, 0.0);
        final Calendar startTime = Calendar.getInstance(Locale.US);

        // Configure TripRepository.getTripByStartEndLocation(...).
        final List<Trip> trips = List.of(Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build());
        when(tripRepository.getTripByStartEndLocation(0.0, 0.0, 0.0, 0.0)).thenReturn(new ArrayList<>());

        // Run the test
//        assertThatThrownBy(() -> tripService.searchTrip(startLocation, endLocation, startTime, 0)).isInstanceOf(
//                Exception.class);
    }

    @Test
    void testStartTrip() throws Exception {
        // Setup
        final Trip expectedResult = Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build();
        Trip trip = Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build();
        Trip trip1 = Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(true)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build();

        // Configure TripRepository.findById(...).
        when(tripRepository.findById(0L)).thenReturn(Optional.ofNullable(trip));
        // Configure TripRepository.save(...).
        when(tripRepository.save(trip1)).thenReturn(expectedResult);

        // Run the test
        final Trip result = tripService.startTrip(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testStartTrip_ThrowsException() throws Exception {
        // Setup
        // Configure TripRepository.findById(...).
        final Optional<Trip> trip = Optional.of(Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build());
        when(tripRepository.findById(0L)).thenReturn(Optional.empty());

        // Configure TripRepository.save(...).
        final Trip trip1 = Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build();
        when(tripRepository.save(Trip.builder()
                .id(0L)
                .startTime(Calendar.getInstance(Locale.US))
                .emptySeat(0)
                .isStarted(false)
                .driver(Client.builder()
                        .id(0L)
                        .build())
                .build())).thenReturn(trip1);

        // Run the test
        assertThatThrownBy(() -> tripService.startTrip(0L)).isInstanceOf(Exception.class);
    }
}