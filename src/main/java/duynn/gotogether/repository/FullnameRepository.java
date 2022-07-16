package duynn.gotogether.repository;

import duynn.gotogether.entity.Fullname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface FullnameRepository extends JpaRepository<Fullname, Long> {
    @Modifying
    int deleteFullnameById(Long id);
}