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
        client = clientRepository.save(client);
        return null;
    }

    @Override
    public Client update(Client client) throws Exception {
        client.setActive(true);
        client = clientRepository.save(client);
        return client;
    }

    @Override
    public int delete(Long id) {
        clientRepository.deleteClient(id);
        return 0;
    }
}
