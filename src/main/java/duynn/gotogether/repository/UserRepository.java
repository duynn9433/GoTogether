package duynn.gotogether.repository;

import duynn.gotogether.entity.Client;
import duynn.gotogether.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByIsActiveIsTrue();
    Optional<User> findByIdAndIsActiveIsTrue(Long id);

    @Modifying
    @Query(value = "UPDATE user u SET is_active = 0 WHERE u.id = ?1",nativeQuery = true)
    int deleteUserById(Long Id);

    Optional<User> findUserByAccount_Username(String username);

}