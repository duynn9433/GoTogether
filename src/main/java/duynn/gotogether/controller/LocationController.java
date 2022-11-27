package duynn.gotogether.controller;

import duynn.gotogether.dto.entity_dto.ClientDTO;
import duynn.gotogether.dto.entity_dto.LocationDTO;
import duynn.gotogether.dto.request.ClientUpdateLocationRequest;
import duynn.gotogether.dto.response.ListLocationResponse;
import duynn.gotogether.dto.response.LocationResponse;
import duynn.gotogether.entity.Client;
import duynn.gotogether.entity.Trip;
import duynn.gotogether.entity.place.Location;
import duynn.gotogether.service.ClientServiceImpl;
import duynn.gotogether.service.LocationServiceImpl;
import duynn.gotogether.service.TripServiceImpl;
import duynn.gotogether.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {
    @Autowired
    ClientServiceImpl clientService;
    @Autowired
    TripServiceImpl tripService;
    @Autowired
    LocationServiceImpl locationService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/update-driver-location")
    public ResponseEntity<?> updateDriverLocation(@RequestBody ClientUpdateLocationRequest request) {
        try{
            //update driver location
            Location driverLocation = modelMapper.map(request.getLocation(), Location.class);
//            Integer res = locationService.updateLocationByClientId(request.getClientId(), driverLocation);
            Location ret = locationService.updateLocation(driverLocation);
            //get passenger locations
            List<Location> passengerLocations = locationService.findPassengerLocationByTripId(request.getTripId());
            List<LocationDTO> passengerLocationDTOs = passengerLocations
                    .stream()
                    .map(location -> modelMapper.map(location, LocationDTO.class))
                    .collect(Collectors.toList());
            //init response
            ListLocationResponse response = new ListLocationResponse();
            response.setLocationList(passengerLocationDTOs);
            response.setStatus(Constants.SUCCESS);
            response.setMessage("Update driver location successfully");
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            ListLocationResponse response = new ListLocationResponse();
            response.setStatus(Constants.FAIL);
            response.setMessage(e.getMessage());
            return ResponseEntity.ok().body(response);
        }
    }

    @PostMapping("/update-passenger-location")
    @Transactional
    public ResponseEntity<?> updatePassengerLocation(@RequestBody ClientUpdateLocationRequest request) {
        try{
            //update passenger location
            Location passengerLocation = modelMapper.map(request.getLocation(), Location.class);
            Location res = locationService.updateLocation(passengerLocation);
            //get driver location
            Trip trip = tripService.findById(request.getTripId());
            Location driverLocation = trip.getDriver().getLocation();
            LocationDTO driverLocationDTO = modelMapper.map(driverLocation, LocationDTO.class);
            //init response
            LocationResponse response = new LocationResponse();
            response.setLocation(driverLocationDTO);
            response.setStatus(Constants.SUCCESS);
            response.setMessage("Update passenger location successfully");
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            ListLocationResponse response = new ListLocationResponse();
            response.setStatus(Constants.FAIL);
            response.setMessage(e.getMessage());
            return ResponseEntity.ok().body(response);
        }
    }

    /**yeu cau gui thong bao toi client*/
}
