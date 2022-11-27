package duynn.gotogether.controller.firebase;

import duynn.gotogether.dto.firebase.*;
import duynn.gotogether.dto.response.Status;
import duynn.gotogether.service.ClientServiceImpl;
import duynn.gotogether.service.firebase.FCMService;
import duynn.gotogether.service.firebase.PushNotificationService;
import duynn.gotogether.util.Constants;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PushNotificationController {

    @Autowired
    private FCMService fcmService;
    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private PushNotificationService pushNotificationService;

    @PostMapping("/notification")
    public String sendSampleNotification(@RequestBody PnsRequest pnsRequest) {
        return fcmService.pushNotification(pnsRequest);
    }

    @PostMapping("/notification/token")
    public ResponseEntity sendTokenNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotificationToToken(request);
        System.out.println("princr");
        return new ResponseEntity<>(
                new PushNotificationResponse(HttpStatus.OK.value(),
                        "Notification has been sent."), HttpStatus.OK);
    }

    @PostMapping("/updatetoken")
    public ResponseEntity<?> updateToken(@RequestBody UpdateTokenRequest request) {
        Status status = new Status();
        try{
            clientService.updateFcmToken(request.getClientId(), request.getToken());
            status.setStatus(Constants.SUCCESS);
            return ResponseEntity.ok(status);
        }catch (Exception e){
            status.setStatus(Constants.FAIL);
            status.setMessage(e.getMessage());
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
    }
}
