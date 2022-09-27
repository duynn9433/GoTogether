package duynn.gotogether.service;

import duynn.gotogether.dto.TripDTO;
import duynn.gotogether.entity.*;
import duynn.gotogether.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    ModelMapper modelMapper;

    public List<TripDTO> findAllDTO() throws Exception {
        List<Trip> trips = tripRepository.findAll();

        List<TripDTO> tripDTOS = new ArrayList<>();
        for (Trip trip : trips) {
            tripDTOS.add(modelMapper.map(trip, TripDTO.class));
        }
        if (trips.isEmpty()) {
            throw new Exception("Không tìm thấy trip");
        }

        return tripDTOS;
    }

    @Override
    public List<Trip> findAll() throws Exception {
        List<Trip> trips = tripRepository.findAll();

        if (trips.isEmpty()) {
            throw new Exception("Không tìm thấy trip");
        }

        return trips;
    }

    @Override
    public Trip findById(Long id) throws Exception {
        Optional<Trip> trip = tripRepository.findById(id);
        if (trip.isPresent()) {
            return trip.get();
        } else {
            throw new Exception("Không tìm thấy trip");
        }
    }
    public TripDTO findByIdDTO(Long id) throws Exception {
        Optional<Trip> trip = tripRepository.findById(id);
        if (trip.isPresent()) {
            return modelMapper.map(trip.get(), TripDTO.class);
        } else {
            throw new Exception("Không tìm thấy trip");
        }
    }


    public TripDTO createDTO(Trip trip) throws Exception {
        Trip responseTrip = tripRepository.save(trip);
        return modelMapper.map(responseTrip, TripDTO.class);
    }
    @Override
    public Trip create(Trip trip) throws Exception {
        Trip responseTrip = tripRepository.save(trip);
        return responseTrip;
    }

    @Override
    public Trip update(Trip trip) throws Exception {
        Trip responseTrip = tripRepository.save(trip);
        return responseTrip;
    }
    public TripDTO updateDTO(Trip trip) throws Exception {
        Trip responseTrip = tripRepository.save(trip);
        return modelMapper.map(responseTrip, TripDTO.class);
    }

    @Override
    public int delete(Long id) {
        return tripRepository.deleteTripById(id);
    }
}
