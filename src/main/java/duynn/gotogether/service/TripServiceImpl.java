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
public class TripServiceImpl implements GeneralService<Trip> {

    @Autowired
    TripRepository tripRepository;
    @Autowired
    ClientTripRepository clientTripRepository;
    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Trip> findAll() throws Exception {
        List<Trip> trips = tripRepository.findAll();
        if(trips.isEmpty()){
            throw new Exception("Không tìm thấy trip");
        }
        return trips;
    }

    @Override
    public Trip findById(Long id) throws Exception {
        Optional<Trip> trip = tripRepository.findById(id);
        if(trip.isPresent()){
            return trip.get();
        }else{
            throw new Exception("Không tìm thấy trip");
        }
    }

    @Override
    public Trip create(Trip trip) throws Exception {
        return tripRepository.save(trip);
    }

    @Override
    public Trip update(Trip trip) throws Exception {
        return tripRepository.save(trip);
    }

    @Override
    public int delete(Long id) {
        return tripRepository.deleteTripById(id);
    }
}
