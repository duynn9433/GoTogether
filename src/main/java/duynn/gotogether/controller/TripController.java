package duynn.gotogether.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import duynn.gotogether.dto.entity_dto.ClientTripDTO;
import duynn.gotogether.dto.entity_dto.TripDTO;
import duynn.gotogether.dto.firebase.PushNotificationRequest;
import duynn.gotogether.dto.request.SearchTripRequest;
import duynn.gotogether.dto.response.Status;
import duynn.gotogether.dto.start_trip_dto.StartTripResponse;
import duynn.gotogether.dto.response.TripResponse;
import duynn.gotogether.dto.response.ListTripResponse;
import duynn.gotogether.entity.Client;
import duynn.gotogether.entity.ClientTrip;
import duynn.gotogether.entity.Comment;
import duynn.gotogether.entity.Trip;
import duynn.gotogether.repository.TripRepository;
import duynn.gotogether.service.ClientServiceImpl;
import duynn.gotogether.service.ClientTripServiceImpl;
import duynn.gotogether.service.CommentServiceImpl;
import duynn.gotogether.service.TripServiceImpl;
import duynn.gotogether.service.firebase.FCMService;
import duynn.gotogether.service.firebase.PushNotificationService;
import duynn.gotogether.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/trip")
public class TripController {
    @Autowired
    TripServiceImpl tripService;
    @Autowired
    ClientTripServiceImpl clientTripService;
    @Autowired
    ClientServiceImpl clientService;
    @Autowired
    CommentServiceImpl commentService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PushNotificationService pushNotificationService;
    @Autowired
    FCMService fcmService;

    @PostMapping("/publish")
    @Transactional
    public ResponseEntity<?> publishTrip(@RequestBody TripDTO tripDTO) {
        TripResponse response = new TripResponse();
        try{
            Trip data = modelMapper.map(tripDTO, Trip.class);
            Client driver = clientService.findById(data.getDriver().getId());
            //check driver is in trip
//            boolean isDriverInTrip = clientService.checkClientIsInTrip(data.getDriver().getId(), true);
            boolean isDriverInTrip = driver.isInTrip();
            if(isDriverInTrip) {
                response.setStatus(Constants.FAIL);
                response.setMessage("Lái xe đã đăng chuyến đi");
                return ResponseEntity.ok().body(response);
            }
            //create fcm topic
            String topic = "trip" + data.getDriver().getId();
            pushNotificationService.createTopic(driver.getFcmToken(), topic);
            data.setFcmTopic(topic);

            //public trip
            Trip trip = tripService.publishTrip(data);
            response.setStatus(Constants.SUCCESS);
            response.setMessage("Publish trip successfully");
            response.setTrip(modelMapper.map(trip, TripDTO.class));
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Constants.FAIL);
            response.setMessage(e.getMessage());
            return ResponseEntity.ok().body(response);
//            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/current/{id}")
    public ResponseEntity<?> getCurrentTrip(@PathVariable("id") Long driverId) {
        TripResponse response = new TripResponse();
        try{
            Trip trip = tripService.getTripNotFinishedByDriverId(driverId);
            response.setStatus(Constants.SUCCESS);
            response.setMessage("Get current trip successfully");
            response.setTrip(modelMapper.map(trip, TripDTO.class));
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Constants.FAIL);
            response.setMessage(e.getMessage());
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get-accepted-trip/{id}")
    public ResponseEntity<?> getAcceptedTrip(@PathVariable("id") Long clientId) {
        TripResponse response = new TripResponse();
        try{
            Trip trip = tripService.getAcceptedTripNotFinished(clientId);
            response.setStatus(Constants.SUCCESS);
            response.setMessage("Get current trip successfully");
            response.setTrip(modelMapper.map(trip, TripDTO.class));
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Constants.FAIL);
            response.setMessage(e.getMessage());
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get-waiting-trip-by-passenger-id/{id}")
    public ResponseEntity<?> getWaitingTripByPassengerId(@PathVariable("id") Long clientId) {
        ListTripResponse response = new ListTripResponse();
        try{
            List<Trip> trips = tripService.getWaitingTripByPassengerId(clientId);
            List<TripDTO> tripDTOS = trips.stream()
                    .map(trip -> modelMapper.map(trip, TripDTO.class))
                    .collect(Collectors.toList());
            response.setStatus(Constants.SUCCESS);
            response.setMessage("Get current trip successfully");
            response.setTrips(tripDTOS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Constants.FAIL);
            response.setMessage(e.getMessage());
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.ok().body(response);
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
            ListTripResponse searchTripResponse = ListTripResponse.builder()
                    .trips(tripDTOS)
                    .status(Constants.SUCCESS)
                    .build();
            return ResponseEntity.ok().body(searchTripResponse);
        } catch (Exception e) {
            e.printStackTrace();
            ListTripResponse searchTripResponse = ListTripResponse.builder()
                    .status(Constants.FAIL)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(searchTripResponse);
        }
    }

    @PostMapping("/start/{id}")
    public ResponseEntity<?> startTrip(@PathVariable("id") Long tripId) {
        StartTripResponse response = new StartTripResponse();
        try{
            Trip res = tripService.startTrip(tripId);
            if(res != null) {
                response.setStatus(Constants.SUCCESS);
                response.setMessage("Start trip successfully");
                response.setTrip(modelMapper.map(res, TripDTO.class));
                List<ClientTrip> clientTrips = clientTripService.getAcceptClientTripsByTripId(tripId);
                List<duynn.gotogether.dto.start_trip_dto.ClientTripDTO> clientTripDTOS = clientTrips.stream()
                        .map(clientTrip -> modelMapper.map(clientTrip, duynn.gotogether.dto.start_trip_dto.ClientTripDTO.class))
                        .collect(Collectors.toList());
                response.setClientTrips(clientTripDTOS);
                //noti to passenger
//                notifyToPassenger(res);
                notifyToPassengerByTopic(res);
            } else {
                response.setStatus(Constants.FAIL);
                response.setMessage("Start trip failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Constants.FAIL);
            response.setMessage(e.getMessage());
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.ok().body(response);
    }

    private void notifyToPassengerByTopic(Trip trip) throws FirebaseMessagingException {
        PushNotificationRequest pushNotificationRequest = PushNotificationRequest.builder()
                .title("Thông báo")
                .message("Chuyến đi " + trip.getId()+ " đã bắt đầu")
                .topic(trip.getFcmTopic())
                .build();
        fcmService.sendMessageToTopic(pushNotificationRequest);
    }

    private void notifyToPassenger(Trip res) throws Exception {
        List<ClientTrip> clientTrips = clientTripService.getAcceptClientTripsByTripId(res.getId());
        List<String> tokens = new ArrayList<>();
        for(ClientTrip clientTrip : clientTrips) {
            tokens.add(clientTrip.getClient().getFcmToken());
        }
        PushNotificationRequest pushNotificationRequest = PushNotificationRequest.builder()
                .title("Thông báo")
                .message("Chuyến đi " + res.getId()+ " đã bắt đầu")
                .build();
        pushNotificationService.sendPushNotiToMultipleToken(pushNotificationRequest,tokens);
    }

//    @PostMapping("/request-finish-passenger")
//public ResponseEntity<?> requestFinishTripByPassenger(@RequestBody ClientTripDTO request) {
//        Status response = new Status();
//        ClientTrip clientTrip = modelMapper.map(request, ClientTrip.class);
//        try{
//            ClientTrip return = clientTripService.requestFinishTripByPassenger(clientTrip);
//            if(res != null) {
//                response.setStatus(Constants.SUCCESS);
//                response.setMessage("Request finish trip successfully");
//                response.setTrip(modelMapper.map(res, TripDTO.class));
//                //noti to driver
//                notifyToDriver(res);
//            } else {
//                response.setStatus(Constants.FAIL);
//                response.setMessage("Request finish trip failed");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.setStatus(Constants.FAIL);
//            response.setMessage(e.getMessage());
//            return ResponseEntity.ok().body(response);
//        }
//        return ResponseEntity.ok().body(response);
//    }
    @PostMapping("/finish/{id}")
    public ResponseEntity<?> finishTrip(@PathVariable("id") Long tripId) {
        Status response = new Status();
        try{
            Trip res = tripService.findById(tripId);
            if(res != null) {
                response.setStatus(Constants.SUCCESS);
                response.setMessage("Finish trip successfully");
                //trip finish
                res.setFinished(true);
                //driver not in trip
                res.getDriver().setInTrip(false);
                //update rating
                List<Comment> comments = commentService.getCommentsByDriverId(res.getDriver().getId());
                int sum =0;
                for(Comment comment : comments) {
                    sum += comment.getRating();
                }
                res.getDriver().setRate((double) sum/comments.size());
                tripService.create(res);//update trip
            } else {
                response.setStatus(Constants.FAIL);
                response.setMessage("Finish trip failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Constants.FAIL);
            response.setMessage(e.getMessage());
            return ResponseEntity.ok().body(response);
        }

        return ResponseEntity.ok().body(response);
    }
}
