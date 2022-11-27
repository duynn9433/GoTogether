package duynn.gotogether.service;

import duynn.gotogether.entity.ContactInfomation;
import duynn.gotogether.repository.ContactInformationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ContactInfoServiceImplTest {

    @Autowired
    private ContactInfoServiceImpl contactInfoService;
    @MockBean(name = "contactInformationRepository")
    private ContactInformationRepository contactInformationRepository;
    @Test
    @Transactional
    @Rollback
    void testCreate() {
        // Setup
        final ContactInfomation contactInfomation = new ContactInfomation(0L, "phoneNumber", "email");
        final ContactInfomation expectedResult = new ContactInfomation(0L, "phoneNumber", "email");

        // Configure ContactInformationRepository.save(...).
        when(contactInformationRepository.save(contactInfomation)).thenReturn(expectedResult);

        // Run the test
        final ContactInfomation result = contactInfoService.create(contactInfomation);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final ContactInfomation expectedResult = new ContactInfomation(0L, "phoneNumber", "email");

        // Configure ContactInformationRepository.findById(...).
        when(contactInformationRepository.findById(0L)).thenReturn(Optional.of(expectedResult));

        // Run the test
        final ContactInfomation result = contactInfoService.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_ThrowsException() {
        // Setup
        // Configure ContactInformationRepository.findById(...).
        when(contactInformationRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> contactInfoService.findById(0L)).isInstanceOf(Exception.class);
    }
}