package duynn.gotogether.repository;

import duynn.gotogether.entity.ClientTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientTripRepository extends JpaRepository<ClientTrip, Long> {
    @Modifying
    int deleteClientTripById(Long id);

    @Query(value = "SELECT * FROM client_trip WHERE trip_id = ?1 AND is_canceled = 0", nativeQuery = true)
    List<ClientTrip> getClientTripsByTripIdAndIsCanceledIsFalse(Long tripId);

    @Query(value = "SELECT * FROM client_trip WHERE trip_id = ?1 AND is_canceled = 0 AND is_accepted = 1", nativeQuery = true)
    List<ClientTrip> getAcceptClientTripsByTripId(Long tripId);

    ClientTrip findByTripIdAndClientId(Long id, Long clientId);

    @Query(value = "select ct.* from client_trip ct, trip t, client c " +
            "where t.id = ct.trip_id " +
            "and t.driver_id = ?1 " +
            "and ct.is_driver_commentted = false " +
            "group by ct.id " +
            "order by ct.id ", nativeQuery = true)
    List<ClientTrip> getClientTripDriverNotCommentByDriverId(Long driverId);

    @Query(value = "select ct.* from client_trip ct " +
            "where ct.client_id = ?1 " +
            "and ct.is_passenger_commentted = false ", nativeQuery = true)
    List<ClientTrip> getClientTripPassengerNotCommentByPassengerId(Long passengerId);
}