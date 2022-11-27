package duynn.gotogether.repository;

import duynn.gotogether.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {
    @Modifying
    int deleteTripById(Long id);

    @Query(value = "CALL go_together.get_trip_by_location(?1, ?2);", nativeQuery = true)
    List<Trip> getTripByLocation(Double lat, Double lng);

    @Query(value = "CALL go_together.get_trip_by_start_end(?1, ?2, ?3, ?4);", nativeQuery = true)
    List<Trip> getTripByStartEndLocation(Double lat1, Double lng1, Double lat2, Double lng2);

    @Modifying
    @Query(value = "UPDATE trip t SET t.is_canceled = ?2  WHERE t.id = ?1", nativeQuery = true)
    Integer cancelTripById(Long id, boolean isCanceled);

    @Modifying
    @Query(value = "UPDATE trip t SET t.is_finished = ?2 WHERE t.id = ?1", nativeQuery = true)
    Integer finishTripById(Long id, boolean isFinished);

    @Modifying
    @Query(value = "UPDATE trip t SET t.is_started = ?2 WHERE t.id = ?1", nativeQuery = true)
    Integer startTripById(Long id, boolean isStarted);

    @Query(value = "SELECT * FROM trip t WHERE t.is_canceled = 0 AND t.is_finished = 0 AND t.driver_id = ?1", nativeQuery = true)
    Optional<Trip> getTripNotFinishedByDriverId(Long id);

    @Query(value = "select t.* from trip t, client c, client_trip ct " +
            "where c.id = ?1 " +
            "and c.id = ct.client_id " +
            "and ct.trip_id = t.id " +
            "and ct.is_accepted = 1 " +
            "and ct.is_canceled = 0 " +
            "and t.is_canceled = 0 " +
            "and t.is_finished = 0", nativeQuery = true)
    Optional<Trip> getAcceptedTripByClientId(Long id);

    @Query(value="select t.* from trip t, client c, client_trip ct " +
            "where c.id = ?1 " +
            "and c.id = ct.client_id " +
            "and ct.trip_id = t.id " +
            "and ct.is_accepted = 0 " +
            "and ct.is_canceled = 0 " +
            "and t.is_canceled = 0 " +
            "and t.is_finished = 0 " +
            "and t.is_started = 0", nativeQuery = true)
    List<Trip> getWaitingTripByPassengerId(Long passengerId);
}