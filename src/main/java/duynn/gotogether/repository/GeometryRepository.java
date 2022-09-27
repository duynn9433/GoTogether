package duynn.gotogether.repository;

import duynn.gotogether.entity.place.Geometry;
import duynn.gotogether.entity.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeometryRepository extends JpaRepository<Geometry, Long> {
}
