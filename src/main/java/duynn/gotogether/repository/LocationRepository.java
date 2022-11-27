package duynn.gotogether.repository;

import duynn.gotogether.entity.place.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "select l.* from location l, client c where c.location_id = l.id and c.id = ?1", nativeQuery = true)
    Location findLocationByClientId(Long clientId);

    @Query(value = "update location l, client c set l.lat = ?2, l.lng =?3 where c.location_id = l.id and c.id = ?1", nativeQuery = true)
    Integer updateLocationByClientId(Long clientId, Double lat, Double lng);

    @Query(value = "select l.* from location l, client c, client_trip ct, trip t " +
            "where ct.trip_id = t.id " +
            "and ct.client_id = c.id " +
            "and c.location_id = l.id " +
            "and t.id = ?1", nativeQuery = true)
    List<Location> findPassengerLocationByTripId(Long tripId);

}
