package duynn.gotogether.controller;

import duynn.gotogether.dto.ApiError;
import duynn.gotogether.dto.entity_dto.ClientDTO;
import duynn.gotogether.dto.request.ClientLocationDTO;
import duynn.gotogether.dto.request.ClientUpdateLocationRequest;
import duynn.gotogether.dto.response.ListLocationResponse;
import duynn.gotogether.dto.response.LocationResponse;
import duynn.gotogether.entity.Client;
import duynn.gotogether.repository.ClientRepository;
import duynn.gotogether.service.ClientServiceImpl;
import duynn.gotogether.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    @Autowired
    ClientServiceImpl clientService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable("id") Long id) {
        try {
            Client client = clientService.findById(id);
            ClientDTO dto = modelMapper.map(client, ClientDTO.class);
            System.out.println("success");
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/update-driver-location")
    public ResponseEntity<?> updateDriverLocation(@RequestBody Client client, @RequestParam List<Long> passengerIds) {
        try {
            System.out.println("passengerIDs:"+passengerIds.toString());
            //update driver location
            Client ret = clientService.updateLocation(client);
            //get passenger locations
            List<Client> passengerLocations = clientService.getListLocationWithId(passengerIds);
            //init response
//            ListLocationResponse response = ListLocationResponse.builder()
//                    .locationList(passengerLocations)
//                    .status(Constants.SUCCESS)
//                    .message("Update driver location successfully")
//                    .build();
            for(Client c: passengerLocations){
                System.out.println("Passenger: " + c.printLocation());
            }
            System.out.println("Driver: (" + ret.getLat() + ", "+ ret.getLng() + ")");
            for(Client c: passengerLocations){
                System.out.println("Driver passen: " + c.getId()+" " + c.printLocation());
            }
            return ResponseEntity.ok().body(passengerLocations);
        } catch (Exception e) {
//            ListLocationResponse response = new ListLocationResponse();
//            response.setStatus(Constants.FAIL);
//            response.setMessage(e.getMessage());
//            return ResponseEntity.ok().body(response);
            return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getMessage()));
        }
    }

    @PostMapping("/update-passenger-location")
    @Transactional
    public ResponseEntity<?> updatePassengerLocation(@RequestBody Client client, @RequestParam Long driverId) {
        try {
            //update passenger location
            Client ret = clientService.updateLocation(client);
            //get driver location
            Client driverLocation = clientService.getLocation(driverId);
            //init response
//            LocationResponse response = LocationResponse.builder()
//                    .location(driverLocation)
//                    .status(Constants.SUCCESS)
//                    .message("Update passenger location successfully")
//                    .build();
            System.out.println("Passenger: (" + ret.getLat() + ", "+ ret.getLng() + ")");
            System.out.println("Passenger update: (" + driverLocation.getLat() + ", "+ driverLocation.getLng() + ")");
            return ResponseEntity.ok().body(driverLocation);
        } catch (Exception e) {
//            ListLocationResponse response = new ListLocationResponse();
//            response.setStatus(Constants.FAIL);
//            response.setMessage(e.getMessage());
//            return ResponseEntity.ok().body(response);
            return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getMessage()));
        }
    }
}
