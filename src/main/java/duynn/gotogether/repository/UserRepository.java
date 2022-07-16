package duynn.gotogether.repository;

import duynn.gotogether.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}