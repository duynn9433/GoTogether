package duynn.gotogether.controller;

import duynn.gotogether.dto.ApiError;
import duynn.gotogether.dto.client_trip_dto.ClientTripDTO;
import duynn.gotogether.dto.client_trip_dto.ClientTripResponse;
import duynn.gotogether.dto.client_trip_dto.ListClientTripResponse;
import duynn.gotogether.dto.entity_dto.CommentDTO;
import duynn.gotogether.dto.firebase.PushNotificationRequest;
import duynn.gotogether.dto.request.ClientLocationDTO;
import duynn.gotogether.dto.request.CommentRequest;
import duynn.gotogether.dto.request.PassengerFinishRequest;
import duynn.gotogether.dto.response.ListCommentResponse;
import duynn.gotogether.dto.response.Status;
import duynn.gotogether.entity.Client;
import duynn.gotogether.entity.ClientTrip;
import duynn.gotogether.entity.Comment;
import duynn.gotogether.entity.Trip;
import duynn.gotogether.repository.ClientRepository;
import duynn.gotogether.repository.ClientTripRepository;
import duynn.gotogether.repository.CommentRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/comment")
public class CommentController {
    @Autowired
    ClientTripServiceImpl clientTripService;
    @Autowired
    CommentServiceImpl commentService;
    @Autowired
    ClientServiceImpl clientService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private CommentRepository commentRepository;


    @GetMapping("/get-all-by-receiver-id/{id}")
    public ResponseEntity<?> getAllByReceiverId(@PathVariable("id") Long id) {
        List<Comment> comments = commentService.getAllByReceiverId(id);
        ListCommentResponse response = new ListCommentResponse();
        if(comments.isEmpty()){
            response.setCommentDTOS(new ArrayList<>());
            response.setStatus(Constants.FAIL);
            response.setMessage("Không tìm thấy đánh giá");
        }else{
            List<CommentDTO> commentDTOS = comments.stream()
                    .map(comment -> modelMapper.map(comment, CommentDTO.class))
                    .collect(Collectors.toList());
            response.setCommentDTOS(commentDTOS);
            response.setStatus(Constants.SUCCESS);
            response.setMessage("Lấy đánh giá thành công");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-received-comment/{id}")
    public ResponseEntity<?> getAllReceivedComment(@PathVariable("id") Long id) {
        List<Comment> comments = commentService.getAllByReceiverId(id);
        if(comments.isEmpty()){
            return ResponseEntity.badRequest().body(
                    new ApiError(HttpStatus.BAD_REQUEST.value(), "Không tìm thấy đánh giá", "Không tìm thấy đánh giá"));
        }else{
            for (Comment comment : comments) {
                comment.setReceiver(Client.builder()
                        .id(id)
                        .fullname(comment.getReceiver().getFullname())
                        .build());
                comment.setSender(Client.builder()
                        .id(id)
                        .fullname(comment.getSender().getFullname())
                        .build());
                comment.setClientTrip(null);
            }
            return ResponseEntity.ok(comments);
        }
    }
    @GetMapping("/get-all-send-comment/{id}")
    public ResponseEntity<?> getAllSendComment(@PathVariable("id") Long id) {
        List<Comment> comments = commentService.getAllSendComment(id);
        if(comments.isEmpty()){
            return ResponseEntity.badRequest().body(
                    new ApiError(HttpStatus.BAD_REQUEST.value(), "Không tìm thấy đánh giá", "Không tìm thấy đánh giá"));
        }else{
            for (Comment comment : comments) {
                comment.setReceiver(Client.builder()
                        .id(id)
                        .fullname(comment.getReceiver().getFullname())
                        .build());
                comment.setSender(Client.builder()
                        .id(id)
                        .fullname(comment.getSender().getFullname())
                        .build());
                comment.setClientTrip(null);
            }
            return ResponseEntity.ok(comments);
        }
    }



    @PostMapping("/create")
    @Transactional
    public ResponseEntity<?> createComment(@RequestBody CommentRequest commentRequest) {
        try{
            //id sender = passsenger -> passenger ||| id sender = driver -> driver
            Comment comment = modelMapper.map(commentRequest.getCommentDTO(), Comment.class);
            //passenger
            if(Objects.equals(comment.getSender().getId(), comment.getClientTrip().getClient().getId())){
                comment.setReceiver(comment.getClientTrip().getTrip().getDriver());
                comment.setSender(comment.getClientTrip().getClient());
                commentService.create(comment);
                //update client trip
                ClientTrip clientTrip = comment.getClientTrip();
                clientTrip.setIsPassengerCommentted(true);
                clientTripService.update(clientTrip);
                //update client rate
                clientService.updateClientRate(comment.getReceiver().getId(), comment.getRating());
            }else {
                comment.setReceiver(comment.getClientTrip().getClient());
                comment.setSender(comment.getClientTrip().getTrip().getDriver());
                commentService.create(comment);
                //update client trip
                ClientTrip clientTrip = comment.getClientTrip();
                clientTrip.setIsDriverCommentted(true);
                clientTripService.update(clientTrip);
                //update client rate
                clientService.updateClientRate(comment.getReceiver().getId(), comment.getRating());
            }
            return ResponseEntity.ok(new Status(Constants.SUCCESS, "Đánh giá thành công"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(new Status(Constants.FAIL, "Đánh giá thất bại"));
        }

    }
}
