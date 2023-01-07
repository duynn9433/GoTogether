package duynn.gotogether.service;

import duynn.gotogether.entity.Trip;
import duynn.gotogether.entity.TripStopPlace;
import duynn.gotogether.entity.place.Geometry;
import duynn.gotogether.entity.place.Location;
import duynn.gotogether.entity.place.GoongPlace;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TripStopPlaceServiceTest {

    //    @Mock
//    @Autowired
//    private TripStopPlaceRepository mockTripStopPlaceRepository;

    //    @InjectMocks
    @Autowired
    private TripStopPlaceService tripStopPlaceServiceUnderTest;

    @Test
    @Transactional
    @Rollback
    void testSave() {
        // Setup
        TripStopPlace tripStopPlace = TripStopPlace.builder()
                .id(null)
                .trip(Trip.builder().id(1L).build())
                .goongPlace(GoongPlace.builder()
                        .name("name")
                        .placeID("placeID")
                        .formattedAddress("formattedAddress")
                        .geometry(Geometry.builder()
                                .location(Location.builder()
                                        .lat(1.0)
                                        .lng(1.0)
                                        .build())
                                .build())
                        .build())
                .build();
        final TripStopPlace expectedResult = TripStopPlace.builder().build();

        // Run the test
        final TripStopPlace result = tripStopPlaceServiceUnderTest.save(tripStopPlace);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
