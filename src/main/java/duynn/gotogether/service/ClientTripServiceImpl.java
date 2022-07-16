package duynn.gotogether.service;

import duynn.gotogether.entity.ClientTrip;
import duynn.gotogether.repository.ClientRepository;
import duynn.gotogether.repository.ClientTripRepository;
import duynn.gotogether.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClientTripServiceImpl implements GeneralService<ClientTrip> {

    @Autowired
    ClientTripRepository clientTripRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    TripRepository tripRepository;


    @Override
    public List<ClientTrip> findAll() throws Exception {
        List<ClientTrip> clientTrips = clientTripRepository.findAll();
        if(clientTrips.isEmpty()){
            throw new Exception("Không tìm thấy clientTrip");
        }
        return clientTrips;
    }

    @Override
    public ClientTrip findById(Long id) throws Exception {
        Optional<ClientTrip> clientTrip = clientTripRepository.findById(id);
        if(clientTrip.isPresent()){
            return clientTrip.get();
        }else{
            throw new Exception("Không tìm thấy clientTrip");
        }
    }

    @Override
    public ClientTrip create(ClientTrip clientTrip) throws Exception {
        return clientTripRepository.save(clientTrip);
    }

    @Override
    public ClientTrip update(ClientTrip clientTrip) throws Exception {
        return clientTripRepository.save(clientTrip);
    }

    @Override
    public int delete(Long id) {
        return clientTripRepository.deleteClientTripById(id);
    }
}
