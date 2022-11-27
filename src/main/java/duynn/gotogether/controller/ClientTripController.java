package duynn.gotogether.controller;

import duynn.gotogether.dto.client_trip_dto.ClientTripDTO;
import duynn.gotogether.dto.client_trip_dto.ClientTripResponse;
import duynn.gotogether.dto.client_trip_dto.ListClientTripResponse;
import duynn.gotogether.dto.entity_dto.CommentDTO;
import duynn.gotogether.dto.firebase.PushNotificationRequest;
import duynn.gotogether.dto.request.PassengerFinishRequest;
import duynn.gotogether.dto.response.Status;
import duynn.gotogether.entity.Client;
import duynn.gotogether.entity.ClientTrip;
import duynn.gotogether.entity.Comment;
import duynn.gotogether.entity.Trip;
import duynn.gotogether.service.ClientServiceImpl;
import duynn.gotogether.service.ClientTripServiceImpl;
import duynn.gotogether.service.CommentServiceImpl;
import duynn.gotogether.service.TripServiceImpl;
import duynn.gotogether.service.firebase.PushNotificationService;
import duynn.gotogether.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/clienttrip")
public class ClientTripController {
    @Autowired
    ClientTripServiceImpl clientTripService;
    @Autowired
    TripServiceImpl tripService;
    @Autowired
    CommentServiceImpl commentService;
    @Autowired
    ClientServiceImpl clientService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PushNotificationService pushNotificationService;

    @GetMapping("/getall/{id}")
    public ResponseEntity<?> getAllClientTripsByTripId(@PathVariable("id") Long id) {
        ListClientTripResponse response = new ListClientTripResponse();
        try {
            List<ClientTrip> list = clientTripService.getClientTripsByTripIdAndIsCanceledIsFalse(id);
            List<ClientTripDTO> listDTO = list
                    .stream()
                    .map(clientTrip -> modelMapper.map(clientTrip, ClientTripDTO.class))
                    .collect(Collectors.toList());
            response.setData(listDTO);
            response.setStatus(Constants.SUCCESS);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(Constants.FAIL);
            return ResponseEntity.ok().body(response);
        }
    }

    @GetMapping("/get-all-accept/{id}")
    public ResponseEntity<?> getAllAccpetClientTripsByTripId(@PathVariable("id") Long id) {
        ListClientTripResponse response = new ListClientTripResponse();
        try {
            List<ClientTrip> list = clientTripService.getAcceptClientTripsByTripId(id);
            List<ClientTripDTO> listDTO = list
                    .stream()
                    .map(clientTrip -> modelMapper.map(clientTrip, ClientTripDTO.class))
                    .collect(Collectors.toList());
            response.setData(listDTO);
            response.setStatus(Constants.SUCCESS);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(Constants.FAIL);
            return ResponseEntity.ok().body(response);
        }
    }

    @PostMapping("/regit")
    @Transactional
    public ResponseEntity<?> regitTrip(@RequestBody ClientTripDTO clientTripDTO) {

        ClientTripResponse response = new ClientTripResponse();
        try {
            System.out.println("DTO "+clientTripDTO);
            ClientTrip clientTrip = modelMapper.map(clientTripDTO, ClientTrip.class);
            System.out.println("Entity "+clientTrip.toString());
            //set price
            Trip trip = tripService.findById(clientTripDTO.getTrip().getId());
            clientTrip.setPricePerKmForOnePeople(trip.getPricePerKm());
            //check is in trip
            Client client = clientService.findById(clientTripDTO.getClient().getId());
            if(client.isInTrip()){
                throw new Exception("Bạn đang tham gia chuyến đi khác");
            }
            //save
            ClientTrip res = clientTripService.create(clientTrip);
            //notify driver
            Client driver = res.getTrip().getDriver();
            PushNotificationRequest request = new PushNotificationRequest();
            request.setTitle("Thông báo");
            request.setMessage("Bạn có 1 yêu cầu tham gia chuyến đi mới");
            request.setToken(driver.getFcmToken());
            pushNotificationService.sendPushNotificationToToken(request);
            //init response
            response.setStatus(Constants.SUCCESS);
            response.setData(modelMapper.map(res, ClientTripDTO.class));
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(Constants.FAIL);
            return ResponseEntity.ok().body(response);
        }
    }

    @PostMapping("/update")
    @Transactional
    public ResponseEntity<?> updateTripStatus(@RequestBody ClientTripDTO clientTripDTO) {
        ClientTripResponse response = new ClientTripResponse();
        try {
            //update to db
            ClientTrip clientTrip = modelMapper.map(clientTripDTO, ClientTrip.class);
            if(clientTrip.isAccepted() && !clientTrip.isCanceled()){
                //update client
                Client client = clientService.findById(clientTrip.getClient().getId());
                client.setInTrip(true);
                clientService.update(client);
                //subcribe client to topic
                Trip trip = tripService.findById(clientTrip.getTrip().getId());
                pushNotificationService.subcribeTopic(client.getFcmToken(),trip.getFcmTopic());
            }
            ClientTrip res = clientTripService.update(clientTrip);

            //init response
            response.setStatus(Constants.SUCCESS);
            response.setData(modelMapper.map(res, ClientTripDTO.class));

            //notify to client
//            Client passenger = clientService.findById(clientTripDTO.getClient().getId());
            Client passenger = res.getClient();
            String fcmToken = passenger.getFcmToken();
            if(fcmToken != null && !fcmToken.isEmpty()){
                PushNotificationRequest request = new PushNotificationRequest();
                request.setTitle("Thông báo");
                if (res.isAccepted() && !res.isCanceled()) {
                    //thong bao toi nguoi dung da dc nhan
                    request.setMessage("Chuyến đi mã số" +res.getTrip().getId() + " của bạn đã được chấp nhận");
                }else{
                    //thong bao toi nguoi dung da bi cancel
                    request.setMessage("Chuyến đi mã số "+res.getTrip().getId() + " của bạn đã bị hủy");
                }
                request.setToken(fcmToken);
                //send
                pushNotificationService.sendPushNotificationToToken(request);
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(Constants.FAIL);
            return ResponseEntity.ok().body(response);
        }
    }

    @PostMapping("/finish")
    @Transactional
    public ResponseEntity<?> passengerFinishTrip(@RequestBody PassengerFinishRequest passengerFinishRequest) {
        Status response = new Status();
        try {
            CommentDTO commentDTO = passengerFinishRequest.getComment();
            Comment comment = modelMapper.map(commentDTO, Comment.class);
            //update client trip
            ClientTrip clientTrip = clientTripService.findById(comment.getClientTrip().getId());
            clientTrip.setAccepted(true);
            clientTrip.setCanceled(true);
            clientTrip.getClient().setInTrip(false);
            clientTripService.update(clientTrip);
//            Client client = clientTrip.getClient();
//            client.setInTrip(false);
//            clientService.update(client);
            //update comment
            comment = commentService.create(comment);
            Status status = new Status(Constants.SUCCESS, "Thành công");
            return ResponseEntity.ok().body(status);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(Constants.FAIL);
            return ResponseEntity.ok().body(response);
        }
    }

}
