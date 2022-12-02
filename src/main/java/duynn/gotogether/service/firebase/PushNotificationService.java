package duynn.gotogether.service.firebase;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.TopicManagementResponse;
import duynn.gotogether.dto.firebase.PushNotificationRequest;
import duynn.gotogether.entity.ClientTrip;
import duynn.gotogether.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PushNotificationService {

    private Logger logger = LoggerFactory.getLogger(PushNotificationService.class);

    private FCMService fcmService;

    public PushNotificationService(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    public void sendPushNotificationToToken(PushNotificationRequest request) {
        try {
            fcmService.sendMessageToToken(request);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void sendPushNotificationToTopic(PushNotificationRequest request) {
        try {
            fcmService.sendMessageToTopic(request);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void sendPushNotiToMultipleToken(PushNotificationRequest request, List<String> listToken) {
        try {
            for (String token : listToken) {
                request.setToken(token);
                fcmService.sendMessageToToken(request);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }



    public TopicManagementResponse createTopic(String token, String topic) throws FirebaseMessagingException {
        List<String> tokenAsList = Arrays.asList(token);
        TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(tokenAsList, topic);
        return response;
    }
    public TopicManagementResponse subcribeTopic(String token, String topic) throws FirebaseMessagingException {
        List<String> tokenAsList = Arrays.asList(token);
        TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(tokenAsList, topic);
        return response;
    }
    public TopicManagementResponse subcribeTopic(List<String> token, String topic) throws FirebaseMessagingException {
        TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(token, topic);
        return response;
    }
    public TopicManagementResponse unSubcribeTopic(String token, String topic) throws FirebaseMessagingException {
        List<String> tokenAsList = Arrays.asList(token);
        TopicManagementResponse response = FirebaseMessaging.getInstance().unsubscribeFromTopic(tokenAsList, topic);
        return response;
    }
    public TopicManagementResponse unSubcribeTopic(List<String> token, String topic) throws FirebaseMessagingException {
        TopicManagementResponse response = FirebaseMessaging.getInstance().unsubscribeFromTopic(token, topic);
        return response;
    }

    public void sendCancelNotiToMultipleClientTrip(List<ClientTrip> clientTrips,
                                                   PushNotificationRequest pushNotificationRequest,
                                                   Long id) {
        Map<String, String> data = new HashMap<>();
        data.put(Constants.CLIENT_TRIP_ID, String.valueOf(clientTrips.get(0).getId()));
        data.put(Constants.PRICE, String.valueOf(0.0));
        data.put(Constants.DISTANCE, String.valueOf(0.0));
        data.put(Constants.PASSENGER_NUM, String.valueOf(0));
        data.put(Constants.DRIVER_ID, String.valueOf(id));

        try {
            for (ClientTrip clientTrip : clientTrips) {
                pushNotificationRequest.setToken(clientTrip.getClient().getFcmToken());
                data.put(Constants.CLIENT_TRIP_ID, String.valueOf(clientTrip.getId()));
                pushNotificationRequest.setData(data);
                fcmService.sendMessageToToken(pushNotificationRequest);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}