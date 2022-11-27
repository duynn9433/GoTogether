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
}