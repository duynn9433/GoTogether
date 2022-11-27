package duynn.gotogether.service;

import duynn.gotogether.entity.Fullname;
import duynn.gotogether.repository.FullnameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
class FullnameServiceImplTest {
    @Autowired
    private FullnameServiceImpl fullnameService;
    @MockBean(name = "fullnameRepository")
    private FullnameRepository fullnameRepository;

    @Test
    void testCreate() {
        // Setup
        final Fullname fullname = new Fullname(0L, "firstName", "lastName", "middleName", "nickName");
        final Fullname expectedResult = new Fullname(0L, "firstName", "lastName", "middleName", "nickName");

        // Configure FullnameRepository.save(...).
        when(fullnameRepository.save(fullname)).thenReturn(expectedResult);

        // Run the test
        final Fullname result = fullnameService.create(fullname);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final Fullname expectedResult = new Fullname(0L, "firstName", "lastName", "middleName", "nickName");

        // Configure FullnameRepository.findById(...).
        when(fullnameRepository.findById(0L)).thenReturn(Optional.of(expectedResult));

        // Run the test
        final Fullname result = fullnameService.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_ThrowsException() {
        // Setup
        // Configure FullnameRepository.findById(...).
        when(fullnameRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> fullnameService.findById(0L)).isInstanceOf(Exception.class);
    }
}