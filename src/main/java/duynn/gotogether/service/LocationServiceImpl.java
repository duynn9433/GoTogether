package duynn.gotogether.service;

import duynn.gotogether.entity.place.Location;
import duynn.gotogether.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class LocationServiceImpl  {
    Logger logger = Logger.getLogger(LocationServiceImpl.class.getName());
    LocationRepository locationRepository;
    Map<Long,Location> locationPool;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
        locationPool = new HashMap<>();
        List<Location> locations = locationRepository.findAll();
        locationPool = locations.stream()
                .collect(Collectors.toMap(Location::getId, location -> location));
//        for (Location location : locations) {
//            locationPool.put(location.getId(),location);
//        }
        logger.info("locationPool" + locationPool.toString());
    }
    public Location newUpdateLocation(Location location){
        logger.info("update locationPool" + location.toString());
        locationPool.put(location.getId(),location);
        return locationPool.get(location.getId());
    }
    public Location newGetLocation(Long id) {
        Location location = locationPool.get(id);
        logger.info("get locationPool" + location.toString());
        if(location == null){
            Optional<Location> data = locationRepository.findById(id);
            if(data.isPresent()){
                location = data.get();
                locationPool.put(location.getId(),location);
            }else{
//                throw new Exception("Không tìm thấy location");
                return new Location(0L,20,20);
            }
        }
        return locationPool.get(id);
    }

    public List<Location> getListLocationWithId(List<Long> listID){
        List<Location> res = new ArrayList<>();
        for(Long id : listID){
            res.add(newGetLocation(id));
        }
        return res;
    }

    public Location findLocationByClientId(Long clientId) throws Exception {
        Location location = locationRepository.findLocationByClientId(clientId);
        if (location == null) {
            throw new Exception("Không tìm thấy vị trí");
        }
        return location;
    }

    @CachePut(value = "locations", key = "#location.id")
    public Location updateLocation(Location location){
        return locationRepository.save(location);
    }
    public Integer updateLocationByClientId(Long clientId, Location location) throws Exception {
        Integer res = locationRepository.updateLocationByClientId(clientId, location.getLat(), location.getLng());
        if (res == 0) {
            throw new Exception("Cập nhật vị trí không thành công");
        }
        return res;
    }
    @CachePut(value = "list_passenger_locations", key = "#tripId")
    public List<Location> findPassengerLocationByTripId(Long tripId) throws Exception {
        List<Location> locations = locationRepository.findPassengerLocationByTripId(tripId);
        if (locations.isEmpty()) {
            throw new Exception("Không tìm thấy vị trí của hành khách");
        }
        return locations;
    }

}
