package duynn.gotogether.repository;

import duynn.gotogether.entity.Client;
import duynn.gotogether.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByIsActiveIsTrue();
    Optional<Client> findByIdAndIsActiveIsTrue(Long id);

    @Modifying
    @Query(value = "UPDATE user u, client c SET u.is_active = 0 WHERE u.id = c.id AND c.id = ?1",nativeQuery = true)
    int deleteClient(Long Id);
    Optional<Client> findClientByAccount_Username(String username);

    @Modifying
    @Query(value = "UPDATE client c SET c.is_in_trip = ?2 WHERE c.id = ?1",nativeQuery = true)
    int setIsInTripById(Long id, Boolean isInTrip);

    @Query(value = "SELECT is_in_trip FROM go_together.client WHERE id = ?1;",nativeQuery = true)
    Optional<Integer> checkClientIsInTrip(Long id);

    Optional<Client> findClientByIdAndIsInTrip(Long id, Boolean isInTrip);

    @Modifying
    @Query(value = "UPDATE client c SET c.fcm_token = ?2 WHERE c.id = ?1",nativeQuery = true)
    Integer updateFcmToken(Long clientId, String token);
}