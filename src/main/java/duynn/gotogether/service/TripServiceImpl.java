package duynn.gotogether.service;

import com.google.maps.model.LatLng;
import duynn.gotogether.entity.*;
import duynn.gotogether.entity.place.Location;
import duynn.gotogether.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import org.apache.commons.lang3.time.DateUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class TripServiceImpl {

    @Autowired
    TripRepository tripRepository;
    @Autowired
    ClientRepository clientRepository;

    public Trip findById(Long id) throws Exception {
        Optional<Trip> trip = tripRepository.findById(id);
        if (trip.isPresent()) {
            return trip.get();
        } else {
            throw new Exception("Không tìm thấy trip");
        }
    }

    public Trip create(Trip trip) {
        return tripRepository.save(trip);
    }

    public int delete(Long id) {
        return tripRepository.deleteTripById(id);
    }

    public List<Trip> getTripByStartEndLocation(LatLng startLocation, LatLng endLocation) throws Exception {
        List<Trip> trips = tripRepository.getTripByStartEndLocation(
                startLocation.lat, startLocation.lng,
                endLocation.lat, endLocation.lng);
        if (trips.isEmpty()) {
            throw new Exception("Không tìm thấy chuyến đi phù hợp");
        }
        return trips;
    }

    @Transactional
    public Trip publishTrip(Trip Trip) throws Exception {
        Trip responseTrip = tripRepository.save(Trip);
        //change driver to in trip
        int result = clientRepository.setIsInTripById(Trip.getDriver().getId(), true);
        if (result == 0) {
            throw new Exception("Không tìm thấy tài xế");
        }
        return responseTrip;
    }

    public Trip getTripNotFinishedByDriverId(Long id) throws Exception {
        Optional<Trip> trip = tripRepository.getTripNotFinishedByDriverId(id);
        if (trip.isPresent()) {
            return trip.get();
        } else {
            throw new Exception("Không tìm thấy chuyến đi");
        }
    }
    @Transactional
    public boolean cancelTrip(Trip trip) throws Exception {
        int resTrip = tripRepository.cancelTripById(trip.getId(), true);
        if (resTrip == 0) {
            throw new Exception("Không tìm thấy chuyến đi");
        }
        //change driver to not in trip
        int result = clientRepository.setIsInTripById(trip.getDriver().getId(), false);
        if (result == 0) {
            throw new Exception("Không tìm thấy tài xế");
        }
        return true;
    }

    @Transactional
    public boolean finishTrip(Trip trip) throws Exception {
        int resTrip = tripRepository.finishTripById(trip.getId(), true);
        if (resTrip == 0) {
            throw new Exception("Không tìm thấy chuyến đi");
        }
        //change driver to not in trip
        int result = clientRepository.setIsInTripById(trip.getDriver().getId(), false);
        if (result == 0) {
            throw new Exception("Không tìm thấy tài xế");
        }
        return true;
    }

    @Transactional
    public Trip startTrip(Long tripId) throws Exception {
        //get trip
        Optional<Trip> tripFromDb = tripRepository.findById(tripId);
        if (tripFromDb.isPresent()) {
            Trip trip  = tripFromDb.get();
            trip.setStarted(true);
            return tripRepository.save(trip);
        } else {
            throw new Exception("Không tìm thấy chuyến đi");
        }
    }
    public List<Trip> searchTrip(LatLng startLocation,
                                 LatLng endLocation,
                                 Calendar startTime,
                                 Integer numOfSeat,
                                 String startPlaceId,
                                 String endPlaceId) throws Exception {
        try {
            List<Trip> trips = getTripByStartEndLocation(startLocation, endLocation);
            System.out.println("trips: " + trips.size());
            //filter by start time and number of seat
            List<Trip> result = new ArrayList<>();
            for (Trip t : trips) {
                if (DateUtils.isSameDay(t.getStartTime(), startTime)
                        && t.getStartTime().get(Calendar.HOUR_OF_DAY) <= startTime.get(Calendar.HOUR_OF_DAY)
                        && t.getEmptySeat() >= numOfSeat) {
                    result.add(t);
                }
            }
//        filter by location1 -> location2
            for(int i = 0; i < result.size(); i++) {
                Trip t = result.get(i);
                //<placeId, index>
                Map<String, Long> temp = new HashMap<>();
                temp.put(t.getStartPlace().getPlaceID(),1L);
                for(int j = 0; j < t.getListStopPlace().size(); j++) {
                    temp.put(t.getListStopPlace().get(j).getPlace().getPlaceID(),j+2L);
                }
                temp.put(t.getEndPlace().getPlaceID(),t.getListStopPlace().size()+2L);
                System.out.println("temp: " + temp.toString());
                System.out.println("startLocation: " + startPlaceId);
                System.out.println("endLocation: " +endPlaceId);
                if(temp.get(startPlaceId) > temp.get(endPlaceId)) {
                    result.remove(i);
                    i--;
                }
            }
            if (result.isEmpty()) {
                throw new Exception();
            }
            return result;
        }catch (Exception e) {
            throw new Exception("Không tìm thấy chuyến đi phù hợp");
        }
    }
    public Trip getAcceptedTripNotFinished(Long clientId) throws Exception {
        Optional<Trip> trip = tripRepository.getAcceptedTripByClientId(clientId);
        if (trip.isPresent()) {
            return trip.get();
        } else {
            throw new Exception("Không tìm thấy chuyến đi");
        }

    }
    public List<Trip> getWaitingTripByPassengerId(Long passengerId) throws Exception {
        List<Trip> trips = tripRepository.getWaitingTripByPassengerId(passengerId);
        if (trips.isEmpty()) {
            throw new Exception("Không tìm thấy chuyến đi phù hợp");
        }else {
            return trips;
        }
    }
}
