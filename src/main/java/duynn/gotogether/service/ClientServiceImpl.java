package duynn.gotogether.service;

import duynn.gotogether.entity.*;
import duynn.gotogether.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClientServiceImpl implements GeneralService<Client> {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    FullnameRepository fullnameRepository;
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    TransportRepository transportRepository;


    @Override
    public List<Client> findAll() throws Exception {
        List<Client> clients = clientRepository.findByIsActiveIsTrue();
        if (clients.isEmpty()) {
            throw new Exception("Không tìm thấy dữ liệu");
        }
        return clients;
    }

    @Override
    public Client findById(Long id) throws Exception {
        Optional<Client> client = clientRepository.findByIdAndIsActiveIsTrue(id);
        if (!client.isPresent()) {
            throw new Exception("Không tìm thấy dữ liệu");
        }
        return client.get();
    }

    @Override
    public Client create(Client client) throws Exception {
        client.setActive(true);
        Client client1 = clientRepository.save(client);
        if (client1 == null) {
            throw new Exception("Không thể tạo mới dữ liệu");
        }
        for(Transport transport : client.getTransports()){
            transport.getOwner().setId(client1.getId());
            transportRepository.save(transport);
        }
        return client1;
    }

    @Override
    public Client update(Client client) throws Exception {
        client.setActive(true);
        return clientRepository.save(client);
    }

    @Override
    public int delete(Long id) {
        return clientRepository.deleteClient(id);
    }
}
