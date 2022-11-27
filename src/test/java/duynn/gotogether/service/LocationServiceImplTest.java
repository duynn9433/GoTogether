package duynn.gotogether.service;

import duynn.gotogether.entity.place.Location;
import duynn.gotogether.repository.LocationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
class LocationServiceImplTest {
    @Autowired
    LocationServiceImpl locationService;
    @MockBean(name = "locationRepository")
    LocationRepository locationRepository;

    @Test
    void testFindLocationByClientId() throws Exception {
        // Setup
        final Location expectedResult = new Location(0L, 0.0, 0.0);
        when(locationRepository.findLocationByClientId(0L)).thenReturn(expectedResult);

        // Run the test
        final Location result = locationService.findLocationByClientId(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindLocationByClientId_ThrowsException() {
        // Setup
        when(locationRepository.findLocationByClientId(0L)).thenReturn(null);

        // Run the test
        assertThatThrownBy(() -> locationService.findLocationByClientId(0L)).isInstanceOf(Exception.class);
    }

    @Test
    void testFindPassengerLocationByTripId() throws Exception {
        // Setup
        final List<Location> expectedResult = List.of(new Location(0L, 0.0, 0.0));
        when(locationRepository.findPassengerLocationByTripId(0L))
                .thenReturn(expectedResult);

        // Run the test
        final List<Location> result = locationService.findPassengerLocationByTripId(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindPassengerLocationByTripId_ThrowsException() {
        // Setup
        when(locationRepository.findPassengerLocationByTripId(0L))
                .thenReturn(new ArrayList<>());

        // Run the test
        assertThatThrownBy(() -> locationService.findPassengerLocationByTripId(0L)).isInstanceOf(Exception.class);
    }

    @Test
    void testUpdateLocation() {
        // Setup
        final Location location = new Location(0L, 0.0, 0.0);
        final Location expectedResult = new Location(0L, 0.0, 0.0);
        when(locationRepository.save(location))
                .thenReturn(expectedResult);

        // Run the test
        final Location result = locationService.updateLocation(location);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateLocationByClientId() throws Exception {
        // Setup
        final Location location = new Location(0L, 0.0, 0.0);
        when(locationRepository.updateLocationByClientId(0L, 0.0, 0.0)).thenReturn(1);

        // Run the test
        final Integer result = locationService.updateLocationByClientId(0L, location);

        // Verify the results
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testUpdateLocationByClientId_ThrowsException() {
        // Setup
        final Location location = new Location(0L, 0.0, 0.0);
        when(locationRepository.updateLocationByClientId(0L, 0.0, 0.0)).thenReturn(0);

        // Run the test
        assertThatThrownBy(() -> locationService.updateLocationByClientId(0L, location)).isInstanceOf(Exception.class);
    }
}