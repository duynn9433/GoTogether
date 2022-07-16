package duynn.gotogether.repository;

import duynn.gotogether.entity.ContactInfomation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface ContactInformationRepository extends JpaRepository<ContactInfomation, Long> {
    @Modifying
    int deleteContactInformationById(Long id);
}