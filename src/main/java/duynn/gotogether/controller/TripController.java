package duynn.gotogether.controller;

import duynn.gotogether.dto.TripDTO;
import duynn.gotogether.dto.request.SearchTripRequest;
import duynn.gotogether.dto.response.SearchTripResponse;
import duynn.gotogether.entity.Trip;
import duynn.gotogether.repository.TripRepository;
import duynn.gotogether.service.TripServiceImpl;
import duynn.gotogether.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/trip")
public class TripController {
    @Autowired
    TripRepository tripRepository;

    @Autowired
    TripServiceImpl tripService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/trips")
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @PostMapping("/trips")
    public ResponseEntity<?> createTrip(@RequestBody TripDTO tripDTO) {
        try{
            Trip trip = tripRepository.save(modelMapper.map(tripDTO, Trip.class));
            return ResponseEntity.ok().body(modelMapper.map(trip, TripDTO.class));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/publish")
    public ResponseEntity<?> publishTrip(@RequestBody TripDTO tripDTO) {
        try{
            Trip trip = tripRepository.save(modelMapper.map(tripDTO, Trip.class));
            ResponseEntity responseEntity = ResponseEntity.ok().body(modelMapper.map(trip, TripDTO.class));
            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/search-with-start-end-location")
    public ResponseEntity<?> searchTripWithStartEndLocation(@RequestParam Double lat1, @RequestParam Double lng1, @RequestParam Double lat2, @RequestParam Double lng2) {
        try{
            List<Trip> trips = tripRepository.getTripByStartEndLocation(lat1, lng1, lat2, lng2);
            List<TripDTO> tripDTOS = new ArrayList<>();
            for (Trip trip : trips) {
                tripDTOS.add(modelMapper.map(trip, TripDTO.class));
            }
            return ResponseEntity.ok().body(tripDTOS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchTrip(@RequestBody SearchTripRequest searchTripRequest) {
        try{
            List<Trip> trips = tripService.searchTrip(
                    searchTripRequest.getStartPlace().getGeometry().getLocation(),
                    searchTripRequest.getEndPlace().getGeometry().getLocation(),
                    searchTripRequest.getStartTime(),
                    searchTripRequest.getNumOfSeat());

            List<TripDTO> tripDTOS = new ArrayList<>();
            for (Trip trip : trips) {
                tripDTOS.add(modelMapper.map(trip, TripDTO.class));
            }
            SearchTripResponse searchTripResponse = SearchTripResponse.builder()
                    .trips(tripDTOS)
                    .status(Constants.SUCCESS)
                    .build();
            return ResponseEntity.ok().body(searchTripResponse);
        } catch (Exception e) {
            e.printStackTrace();
            SearchTripResponse searchTripResponse = SearchTripResponse.builder()
                    .status(Constants.FAIL)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(searchTripResponse);
        }
    }

}
