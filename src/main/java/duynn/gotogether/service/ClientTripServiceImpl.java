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
public class ClientTripServiceImpl{

    @Autowired
    ClientTripRepository clientTripRepository;

    public ClientTrip findById(Long id) throws Exception {
        Optional<ClientTrip> clientTrip = clientTripRepository.findById(id);
        if(clientTrip.isPresent()){
            return clientTrip.get();
        }else{
            throw new Exception("Không tìm thấy clientTrip");
        }
    }

    public ClientTrip create(ClientTrip clientTrip) {
        return clientTripRepository.save(clientTrip);
    }

    public ClientTrip update(ClientTrip clientTrip){
        return clientTripRepository.save(clientTrip);
    }

    public int delete(Long id) {
        return clientTripRepository.deleteClientTripById(id);
    }

    @Transactional
    public List<ClientTrip> getClientTripsByTripIdAndIsCanceledIsFalse(Long tripId) throws Exception {
        List<ClientTrip> clientTrips = clientTripRepository.getClientTripsByTripIdAndIsCanceledIsFalse(tripId);
        if(clientTrips.isEmpty()){
            throw new Exception("Không tìm thấy clientTrip");
        }
        return clientTrips;
    }

    @Transactional
    public List<ClientTrip> getAcceptClientTripsByTripId(Long tripId) throws Exception {
        List<ClientTrip> clientTrips = clientTripRepository.getAcceptClientTripsByTripId(tripId);
        if(clientTrips.isEmpty()){
            throw new Exception("Không tìm thấy clientTrip");
        }
        return clientTrips;
    }


    public Integer passengerFinishTrip(ClientTrip clientTrip){
        ClientTrip res = clientTripRepository.save(clientTrip);
        return 1;
    }

    public ClientTrip findByTripIdAndClientId(Long id, Long clientId) {
        return clientTripRepository.findByTripIdAndClientId(id, clientId);
    }
}
