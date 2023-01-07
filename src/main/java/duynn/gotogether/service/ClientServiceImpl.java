package duynn.gotogether.service;

import duynn.gotogether.dto.request.ClientLocationDTO;
import duynn.gotogether.entity.*;
import duynn.gotogether.entity.place.Location;
import duynn.gotogether.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClientServiceImpl implements GeneralService<Client> {
    private ClientRepository clientRepository;
    private TransportRepository transportRepository;
    private CommentRepository commentRepository;
    private Map<Long, Client> locationPool;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, TransportRepository transportRepository, CommentRepository commentRepository) {
        this.clientRepository = clientRepository;
        this.transportRepository = transportRepository;
        this.commentRepository = commentRepository;
        this.locationPool = new HashMap<>();
    }

    public Client updateLocation(Client location) {
        locationPool.put(location.getId(),location);
        return locationPool.get(location.getId());
    }

    public Client getLocation(Long id) {
        Client location = locationPool.get(id);
        if(location == null){
            Optional<Client> data = clientRepository.findById(id);
            if(data.isPresent()){
                Client client = data.get();
                location = Client.builder()
                        .id(client.getId())
                        .lat(client.getLat())
                        .lng(client.getLng())
                        .build();
                locationPool.put(location.getId(),location);
            }else{
//                throw new Exception("Không tìm thấy location");
                return Client.builder()
                        .id(id)
                        .lat(20.0)
                        .lng(20.0)
                        .build();
            }
        }
        return locationPool.get(id);
    }

    public List<Client> getListLocationWithId(List<Long> listID){
        List<Client> res = new ArrayList<>();
        for(Long id : listID){
            res.add(getLocation(id));
        }
        return res;
    }

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

    public void updateClientRate(Long id, Integer plusRating) {
        commentRepository.updateClientRate(id, plusRating);
    }


}
