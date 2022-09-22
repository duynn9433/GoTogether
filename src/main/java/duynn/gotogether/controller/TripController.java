package duynn.gotogether.controller;

import duynn.gotogether.entity.Trip;
import duynn.gotogether.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/account")
public class TripController {
    @Autowired
    TripRepository tripRepository;

    @GetMapping("/trips")
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @PostMapping("/trips")
    public ResponseEntity<?> createTrip(@RequestBody Trip trip) {
        try{
            tripRepository.save(trip);
            return ResponseEntity.ok().body(trip);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
