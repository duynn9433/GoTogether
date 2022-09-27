package duynn.gotogether.controller;

import duynn.gotogether.dto.TripDTO;
import duynn.gotogether.entity.Trip;
import duynn.gotogether.repository.TripRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/trip")
public class TripController {
    @Autowired
    TripRepository tripRepository;

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
            return ResponseEntity.ok().body(modelMapper.map(trip, TripDTO.class));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
