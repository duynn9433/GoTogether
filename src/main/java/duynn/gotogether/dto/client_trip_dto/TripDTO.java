package duynn.gotogether.dto.client_trip_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripDTO implements Serializable {
    private static final long serialVersionUID = 9L;
    private Long id;

    @JsonProperty("fcm_topic")
    private String fcmTopic;
}
