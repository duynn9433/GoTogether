package duynn.gotogether.repository;

import duynn.gotogether.entity.place.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
