package duynn.gotogether.repository;

import duynn.gotogether.entity.place.GoongPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<GoongPlace, Long> {
}
