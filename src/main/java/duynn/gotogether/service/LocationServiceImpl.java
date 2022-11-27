package duynn.gotogether.service;

import duynn.gotogether.entity.place.Location;
import duynn.gotogether.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class LocationServiceImpl  {
    @Autowired
    LocationRepository locationRepository;

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
