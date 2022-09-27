package duynn.gotogether.controller;

import com.google.gson.Gson;
import duynn.gotogether.dto.*;
import duynn.gotogether.entity.Client;
import duynn.gotogether.entity.Transport;
import duynn.gotogether.entity.Trip;
import duynn.gotogether.entity.place.Geometry;
import duynn.gotogether.entity.place.Location;
import duynn.gotogether.entity.place.Place;
import duynn.gotogether.repository.GeometryRepository;
import duynn.gotogether.repository.LocationRepository;
import duynn.gotogether.repository.PlaceRepository;
import duynn.gotogether.repository.TripRepository;
import duynn.gotogether.service.ClientServiceImpl;
import duynn.gotogether.service.TransportServiceImpl;
import duynn.gotogether.service.TripServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class GetAllController {
    @Autowired
    TripRepository tripRepository;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    GeometryRepository geometryRepository;

    @Autowired
    LocationRepository locationRepository;
    @Autowired
    Gson gson;

    @Autowired
    TripServiceImpl tripService;

    @Autowired
    ClientServiceImpl clientService;

    @Autowired
    TransportServiceImpl transportService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/transports")
    public ResponseEntity<?> getAllTransports() throws Exception {
        List<Transport> transports = transportService.findAll();
        List<TransportDTO> transportDTOS = new ArrayList<>();
        for (Transport transport : transports) {
            transportDTOS.add(modelMapper.map(transport, TransportDTO.class));
        }

        return ResponseEntity.ok(transportDTOS);
    }

    @GetMapping("/clients")
    public ResponseEntity<?> getAllClients() throws Exception {
        List<Client> clients = clientService.findAll();
        List<ClientDTO> clientDTOS = new ArrayList<>();
        for (Client client : clients) {
            clientDTOS.add(modelMapper.map(client, ClientDTO.class));
        }
        System.out.println("getall"+ clientDTOS);
        return ResponseEntity.ok(clientDTOS);
    }

    @GetMapping("/geometry")
    public ResponseEntity getAllGeometry() {
        List<Geometry> geometryList = geometryRepository.findAll();
        List<GeometryDTO> geometryDTOList = new ArrayList<>();
        for (Geometry geometry : geometryList) {
            geometryDTOList.add(modelMapper.map(geometry, GeometryDTO.class));
        }
        return ResponseEntity.ok().body(geometryDTOList);
    }
    @GetMapping("/locations")
    public List getAllLocation() {
        return locationRepository.findAll();
    }

    @GetMapping("/places")
    public List getAllPlaces() {

        List<Place> places = placeRepository.findAll();
        List<PlaceDTO> placeDTOS = new ArrayList<>();
        for (Place place : places) {
            placeDTOS.add(modelMapper.map(place, PlaceDTO.class));
        }
        return placeDTOS;
    }

    @GetMapping("/trips")
    public List<TripDTO> getAllTrips() throws Exception {
        List<Trip> listTip = tripService.findAll();
        System.out.println("listTrip" + listTip);
        List<TripDTO> tripDTOS = new ArrayList<>();
        for (Trip trip : listTip) {
            tripDTOS.add(modelMapper.map(trip, TripDTO.class));
        }
        return tripDTOS;
    }

    @PostMapping("/locations")
    public ResponseEntity<?> createLocation(@RequestBody LocationDTO locationDTO) {
        return ResponseEntity.ok().body(locationRepository.save(modelMapper.map(locationDTO, Location.class)));
    }

    @PostMapping("/places")
    public ResponseEntity<?> createPlace(@RequestBody PlaceDTO placeDTO) {
        return ResponseEntity.ok().body(placeRepository.save(modelMapper.map(placeDTO, Place.class)));
    }

    @PostMapping("/geometry")
    public ResponseEntity<?> createGeometry(@RequestBody GeometryDTO geometryDTO) {
        return ResponseEntity.ok().body(geometryRepository.save(modelMapper.map(geometryDTO, Geometry.class)));
    }

    @PostMapping("/trips")
    public ResponseEntity<?> createTrip(@RequestBody TripDTO tripDTO) {
        return ResponseEntity.ok().body(tripRepository.save(modelMapper.map(tripDTO, Trip.class)));
    }

    @PostMapping("/transport")
    public ResponseEntity<?> createTransport(@RequestBody TransportDTO transportDTO) throws Exception {
        return ResponseEntity.ok().body(transportService.create(modelMapper.map(transportDTO, Transport.class)));
    }

    @PostMapping("/client")
    public ResponseEntity<?> createClient(@RequestBody ClientDTO clientDTO) throws Exception {
        return ResponseEntity.ok().body(clientService.create(modelMapper.map(clientDTO, Client.class)));
    }

}
