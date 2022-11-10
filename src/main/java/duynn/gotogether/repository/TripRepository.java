package duynn.gotogether.repository;

import duynn.gotogether.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    @Modifying
    int deleteTripById(Long id);

    @Query(value = "CALL go_together.get_trip_by_location(?1, ?2);", nativeQuery = true)
    List<Trip> getTripByLocation(Double lat, Double lng);

    @Query(value = "CALL go_together.get_trip_by_start_end(?1, ?2, ?3, ?4);", nativeQuery = true)
    List<Trip> getTripByStartEndLocation(Double lat1, Double lng1, Double lat2, Double lng2);

}