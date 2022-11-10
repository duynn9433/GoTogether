package duynn.gotogether.service;

import duynn.gotogether.dto.TripDTO;
import duynn.gotogether.entity.*;
import duynn.gotogether.entity.place.Location;
import duynn.gotogether.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.time.DateUtils;

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

    @Override
    public int delete(Long id) {
        return tripRepository.deleteTripById(id);
    }

    public List<Trip> getTripByLocation(Location location) throws Exception {
        List<Trip> trips = tripRepository.getTripByLocation(location.getLat(), location.getLng());
        if(trips.isEmpty()){
            throw new Exception("Không tìm thấy chuyến đi phù hợp");
        }
        return trips;
    }

    public List<Trip> getTripByStartEndLocation(Location startLocation, Location endLocation) throws Exception {
        List<Trip> trips = tripRepository.getTripByStartEndLocation(
                startLocation.getLat(), startLocation.getLng(),
                endLocation.getLat(), endLocation.getLng());
        if(trips.isEmpty()){
            throw new Exception("Không tìm thấy chuyến đi phù hợp");
        }
        return trips;
    }

    public List<Trip> searchTrip(Location startLocation,
                                 Location endLocation,
                                 Calendar startTime,
                                 Integer numOfSeat) throws Exception {
        List<Trip> trips = getTripByStartEndLocation(startLocation,endLocation);
        List<Trip> result = new ArrayList<>();
        for(Trip t : trips){
            if(DateUtils.isSameDay(t.getStartTime(), startTime)
                    && t.getEmptySeat() >= numOfSeat){
                result.add(t);
            }
        }
        if(result.isEmpty()){
            throw new Exception("Không tìm thấy chuyến đi phù hợp");
        }
        return result;
    }
}
