package duynn.gotogether.service;

import duynn.gotogether.entity.Address;
import duynn.gotogether.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AddressServiceImpl implements GeneralService<Address> {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<Address> findAll() throws Exception {
        List<Address> addresses = addressRepository.findAll();
        if(addresses.isEmpty()) {
            throw new Exception("Không tìm thấy dữ liệu");
        }
        return addresses;
    }

    @Override
    public Address findById(Long id) throws Exception {
        Optional<Address> address = addressRepository.findById(id);
        if(!address.isPresent()) {
            throw new Exception("Không tìm thấy dữ liệu");
        }
        return address.get();
    }

    @Override
    public Address create(Address address) throws Exception {
        return addressRepository.save(address);
    }

    @Override
    public Address update(Address address) throws Exception {
        return addressRepository.save(address);
    }

    @Override
    public int delete(Long id) {
        return addressRepository.deleteAddressById(id);
    }
}
