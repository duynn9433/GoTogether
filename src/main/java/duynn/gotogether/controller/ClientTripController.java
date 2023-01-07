package duynn.gotogether.controller;

import duynn.gotogether.dto.ApiError;
import duynn.gotogether.dto.client_trip_dto.ClientTripDTO;
import duynn.gotogether.dto.client_trip_dto.ClientTripResponse;
import duynn.gotogether.dto.client_trip_dto.ListClientTripResponse;
import duynn.gotogether.dto.entity_dto.CommentDTO;
import duynn.gotogether.dto.firebase.PushNotificationRequest;
import duynn.gotogether.dto.request.PassengerFinishRequest;
import duynn.gotogether.dto.response.Status;
import duynn.gotogether.entity.*;
import duynn.gotogether.repository.ClientRepository;
import duynn.gotogether.repository.ClientTripRepository;
import duynn.gotogether.service.ClientServiceImpl;
import duynn.gotogether.service.ClientTripServiceImpl;
import duynn.gotogether.service.CommentServiceImpl;
import duynn.gotogether.service.TripServiceImpl;
import duynn.gotogether.service.firebase.PushNotificationService;
import duynn.gotogether.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientTripRepository clientTripRepository;

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
    @Transactional()
    public ResponseEntity<?> regitTrip(@RequestBody ClientTrip clientTrip) {

        System.out.println("regitTrip"+clientTrip.toString());
//        ClientTripResponse response = new ClientTripResponse();
        try {
            //mapper data
//            ClientTrip clientTrip = modelMapper.map(clientTrip, ClientTrip.class);
            System.out.println("regitTrip mapper"+clientTrip.toString());
            clientTrip.setIsDriverCommentted(false);
            clientTrip.setIsPassengerCommentted(false);

            //check clienttrip is exist
            ClientTrip test = clientTripService.findByTripIdAndClientId(
                    clientTrip.getTrip().getId(), clientTrip.getClient().getId());
            if(test != null) {
                throw new Exception("Bạn đã đăng ký chuyến đi này rồi");
            }

            //set price
            Trip trip = tripService.findById(clientTrip.getTrip().getId());
            clientTrip.setPricePerKmForOnePeople(trip.getPricePerKm());

            //update trip empty seat
            trip.setEmptySeat(trip.getEmptySeat() - clientTrip.getNumOfPeople());
            if(trip.getEmptySeat() < 0) {
                throw new Exception("Số chỗ trống không đủ");
            }

            //check is in trip
//            Client client = clientService.findById(clientTripDTO.getClient().getId());
//            if(client.isInTrip()){
//                throw new Exception("Bạn đang tham gia chuyến đi khác");
//            }

            //save
            ClientTrip res = clientTripService.create(clientTrip);

            //notify driver
            Client driver = trip.getDriver();
            PushNotificationRequest request = new PushNotificationRequest();
            request.setTitle("Thông báo");
            request.setMessage("Bạn có 1 yêu cầu tham gia chuyến đi mới");
            request.setToken(driver.getFcmToken());
            pushNotificationService.sendPushNotificationToToken(request);

            //init response
//            response.setStatus(Constants.SUCCESS);
//            response.setData(modelMapper.map(res, ClientTripDTO.class));
            res.getClient().setTransports(null);
            Trip t = res.getTrip();
            res.setTrip(Trip.builder()
                    .id(t.getId())
                    .fcmTopic(t.getFcmTopic())
                    .build());
            return ResponseEntity.ok().body(res);

        } catch (Exception e) {
            e.printStackTrace();
//            response.setMessage(e.getMessage()+"");
//            response.setStatus(Constants.FAIL);
//            return ResponseEntity.ok().body(response);
            return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_REQUEST.value(),e.getMessage(), e.getMessage()));
        }
    }

    @PostMapping("/update")
    @Transactional
    public ResponseEntity<?> updateTripStatus(@RequestBody ClientTrip clientTrip) {
//        ClientTripResponse response = new ClientTripResponse();
        try {
            //update to db
//            ClientTrip clientTrip = modelMapper.map(clientTrip, ClientTrip.class);
            if(clientTrip.isAccepted() && !clientTrip.isCanceled()){
                //update client
                Client client = clientService.findById(clientTrip.getClient().getId());
//                client.setInTrip(true);
                clientService.update(client);
                //subcribe client to topic
                Trip trip = tripService.findById(clientTrip.getTrip().getId());
                pushNotificationService.subcribeTopic(client.getFcmToken(),trip.getFcmTopic());
            }
            ClientTrip res = clientTripService.update(clientTrip);

            //init response
//            response.setStatus(Constants.SUCCESS);
//            response.setData(modelMapper.map(res, ClientTripDTO.class));

            //notify to client
//            Client passenger = clientService.findById(clientTripDTO.getClient().getId());
            Client passenger = res.getClient();
            String fcmToken = passenger.getFcmToken();
            if(fcmToken != null && !fcmToken.isEmpty()){
                PushNotificationRequest request = new PushNotificationRequest();
                request.setTitle("Thông báo");
                if (res.isAccepted() && !res.isCanceled()) {
                    //thong bao toi nguoi dung da dc nhan
                    request.setMessage("Chuyến đi mã số " +res.getTrip().getId() + " của bạn đã được chấp nhận");
                }else{
                    //thong bao toi nguoi dung da bi cancel
                    request.setMessage("Chuyến đi mã số "+res.getTrip().getId() + " của bạn đã bị hủy");
                }
                request.setToken(fcmToken);
                //send
                pushNotificationService.sendPushNotificationToToken(request);
            }
            return ResponseEntity.ok().body(clientTrip);
        } catch (Exception e) {
//            response.setMessage(e.getMessage());
//            response.setStatus(Constants.FAIL);
//            return ResponseEntity.ok().body(response);
            return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_REQUEST.value(),e.getMessage(), e.getMessage()));
        }
    }

    @PostMapping("/finish")
    @Transactional
    public ResponseEntity<?> passengerFinishTrip(@RequestBody Comment comment) {
        System.out.println("Comment req: "+comment.toString());
        Status response = new Status();
        try {
//            CommentDTO commentDTO = commentRequest.getComment();
//            Comment comment = modelMapper.map(commentDTO, Comment.class);
            ClientTrip clientTrip = clientTripService.findById(comment.getClientTrip().getId());
            //set comment
            comment.setSender(Client.builder().id(clientTrip.getClient().getId()).build());
            comment.setReceiver(Client.builder().id(clientTrip.getTrip().getDriver().getId()).build());
            //update client trip
            clientTrip.setAccepted(true);
            clientTrip.setCanceled(true);
            clientTrip.setIsPassengerCommentted(true);
            clientTrip.getClient().setInTrip(false);
            clientTrip.setDistance(comment.getClientTrip().getDistance());
            clientTripService.update(clientTrip);
//            Client client = clientTrip.getClient();
//            client.setInTrip(false);
//            clientService.update(client);
            //update comment
            comment = commentService.create(comment);
            Status status = new Status(Constants.SUCCESS, "Thành công");
            return ResponseEntity.ok().body(status);
        } catch (Exception e) {
            e.printStackTrace();
//            response.setMessage(e.getMessage());
//            response.setStatus(Constants.FAIL);
            return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_REQUEST.value(),e.getMessage(), e.getMessage()));
        }
    }

    @PostMapping("/cancel/{tripId}/{clientId}")
    public ResponseEntity<?> passengerCancelTrip(@PathVariable("tripId") Long tripId,
                                                 @PathVariable("clientId") Long clientId) {
        Status response = new Status();
        try {
            ClientTrip clientTrip = clientTripService.findByTripIdAndClientId(tripId, clientId);
            clientTrip.setCanceled(true);
            clientTrip.setAccepted(false);
            clientTrip.getClient().setInTrip(false);
            clientTripService.update(clientTrip);
            Status status = new Status(Constants.SUCCESS, "Thành công");
            return ResponseEntity.ok().body(status);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(Constants.FAIL);
            return ResponseEntity.ok().body(response);
        }
    }

    @GetMapping("/get-client-trip-uncommented/{clientId}")
    public ResponseEntity<?> getClientTripUncommented(@PathVariable("clientId") Long clientId) {
        duynn.gotogether.dto.response.ListClientTripResponse response = new duynn.gotogether.dto.response.ListClientTripResponse();
        try {
            List<ClientTrip> clientTrips1 = clientTripService.getClientTripDriverNotCommentByDriverId(clientId);
            List<ClientTrip> clientTrips2 = clientTripService.getClientTripPassengerNotCommentByPassengerId(clientId);
            clientTrips1.addAll(clientTrips2);
            List<duynn.gotogether.dto.entity_dto.ClientTripDTO> clientTripDTOS = clientTrips1.stream()
                    .map(clientTrip -> modelMapper.map(
                            clientTrip,
                            //full topping
                            duynn.gotogether.dto.entity_dto.ClientTripDTO.class))
                    .collect(Collectors.toList());
            response.setStatus(Constants.SUCCESS);
            response.setData(clientTripDTOS);
            response.setMessage("Thành công");

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(Constants.FAIL);
            return ResponseEntity.ok().body(response);
        }
    }
}
