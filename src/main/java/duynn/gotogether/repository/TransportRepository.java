package duynn.gotogether.repository;

import duynn.gotogether.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface TransportRepository extends JpaRepository<Transport, Long> {
    @Modifying
    int deleteTransportById(Long id);
}