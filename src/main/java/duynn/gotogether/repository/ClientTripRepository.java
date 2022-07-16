package duynn.gotogether.repository;

import duynn.gotogether.entity.ClientTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface ClientTripRepository extends JpaRepository<ClientTrip, Long> {
    @Modifying
    int deleteClientTripById(Long id);
}