package duynn.gotogether.repository;

import duynn.gotogether.entity.Position;
import duynn.gotogether.entity.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
