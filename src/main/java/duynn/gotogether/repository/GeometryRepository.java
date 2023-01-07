package duynn.gotogether.repository;

import duynn.gotogether.entity.place.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeometryRepository extends JpaRepository<Geometry, Long> {
}
