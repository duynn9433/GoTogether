package duynn.gotogether.repository;

import duynn.gotogether.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByUsername(String username);

    @Modifying
    int deleteAccountById(Long id);

}