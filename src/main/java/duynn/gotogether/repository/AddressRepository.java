package duynn.gotogether.repository;

import duynn.gotogether.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Modifying
    int deleteAddressById(Long id);
}