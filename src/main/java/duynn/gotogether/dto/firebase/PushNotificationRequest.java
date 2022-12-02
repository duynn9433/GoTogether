package duynn.gotogether.dto.firebase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PushNotificationRequest {
    private String title;
    private String message;
    private String topic;
    private String token;
    private Map<String, String> data;
}