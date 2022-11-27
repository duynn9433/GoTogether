package duynn.gotogether.service;

import duynn.gotogether.entity.*;
import duynn.gotogether.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClientServiceImpl implements GeneralService<Client> {

    @Autowired
    ClientRepository clientRepository;
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
        if (client.isEmpty()) {
            throw new Exception("Không tìm thấy dữ liệu");
        }
        return client.get();
    }

    @Override
    public Client create(Client client) {
        client.setActive(true);
        Client client1 = clientRepository.save(client);
        for(Transport transport : client.getTransports()){
            transport.getOwner().setId(client1.getId());
            transportRepository.save(transport);
        }
        return client1;
    }

    @Override
    public Client update(Client client) {
        client.setActive(true);
        return clientRepository.save(client);
    }
    @Override
    public int delete(Long id) {
        return clientRepository.deleteClient(id);
    }

    public Integer updateFcmToken(Long clientId, String token) {
        int result = clientRepository.updateFcmToken(clientId, token);
        if (result == 0) {
            throw new RuntimeException("Không thể cập nhật token");
        }
        return result;
    }
}
