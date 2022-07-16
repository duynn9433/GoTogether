package duynn.gotogether.repository;

import duynn.gotogether.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface TripRepository extends JpaRepository<Trip, Long> {
    @Modifying
    int deleteTripById(Long id);
}