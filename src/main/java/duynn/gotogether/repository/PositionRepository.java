package duynn.gotogether.repository;

import duynn.gotogether.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface PositionRepository extends JpaRepository<Position, Long> {

    @Modifying
    int deletePositionById(Long id);
}