package duynn.gotogether.service;

import duynn.gotogether.entity.Address;
import duynn.gotogether.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class AddressServiceImplTest {
    @Autowired
    AddressServiceImpl addressService;
    @MockBean(name = "addressRepository")
    AddressRepository addressRepository;

    @Test
    void testCreate() throws Exception {
        // Setup
        final Address address = new Address(0L, "city", "district", "province", "detail");
        final Address expectedResult = new Address(0L, "city", "district", "province", "detail");

        // Configure AddressRepository.save(...).
        final Address address1 = new Address(0L, "city", "district", "province", "detail");
        when(addressService.create(
                new Address(0L, "city", "district", "province", "detail"))).thenReturn(address1);

        // Run the test
        final Address result = addressService.create(address);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() {
        // Setup
        when(addressRepository.deleteAddressById(0L)).thenReturn(0);

        // Run the test
        final int result = addressService.delete(0L);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testFindAll() throws Exception {
        // Setup
        final List<Address> expectedResult = List.of(new Address(0L, "city", "district", "province", "detail"));

        // Configure AddressRepository.findAll(...).
        final List<Address> addresses = List.of(new Address(0L, "city", "district", "province", "detail"));
        when(addressRepository.findAll()).thenReturn(addresses);

        // Run the test
        final List<Address> result = addressService.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_AddressRepositoryReturnsNoItems() {
        // Setup
        when(addressRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> addressService.findAll()).isInstanceOf(Exception.class);
    }

    @Test
    void testFindAll_ThrowsException() {
        // Setup
        // Configure AddressRepository.findAll(...).
        final List<Address> addresses = List.of(new Address(0L, "city", "district", "province", "detail"));
        when(addressRepository.findAll()).thenReturn(null);

        // Run the test
        assertThatThrownBy(() -> addressService.findAll()).isInstanceOf(Exception.class);
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final Address expectedResult = new Address(0L, "city", "district", "province", "detail");

        // Configure AddressRepository.findById(...).
        final Optional<Address> address = Optional.of(new Address(0L, "city", "district", "province", "detail"));
        when(addressRepository.findById(0L)).thenReturn(address);

        // Run the test
        final Address result = addressService.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_AddressRepositoryReturnsAbsent() {
        // Setup
        when(addressRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> addressService.findById(0L)).isInstanceOf(Exception.class);
    }

    @Test
    void testFindById_ThrowsException() {
        // Setup
        // Configure AddressRepository.findById(...).
        final Optional<Address> address = Optional.of(new Address(0L, "city", "district", "province", "detail"));
        when(addressRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> addressService.findById(0L)).isInstanceOf(Exception.class);
    }

    @Test
    void testUpdate() throws Exception {
        // Setup
        final Address address = new Address(0L, "city", "district", "province", "detail");
        final Address expectedResult = new Address(0L, "city", "district", "province", "detail");

        // Configure AddressRepository.save(...).
        final Address address1 = new Address(0L, "city", "district", "province", "detail");
        when(addressRepository.save(
                new Address(0L, "city", "district", "province", "detail"))).thenReturn(address1);

        // Run the test
        final Address result = addressService.update(address);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}