package duynn.gotogether.repository;

import duynn.gotogether.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByIsActiveIsTrue();
    Optional<Client> findByIdAndIsActiveIsTrue(Long id);

    @Modifying
    @Query(value = "UPDATE client SET is_active = 0 WHERE Id = ?1",nativeQuery = true)
    int deleteClient(Long Id);

}