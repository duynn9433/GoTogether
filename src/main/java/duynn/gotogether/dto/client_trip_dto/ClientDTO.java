package duynn.gotogether.dto.client_trip_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import duynn.gotogether.dto.entity_dto.LocationDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO extends UserDTO implements Serializable {
    private static final long serialVersionUID = 5L;
    private LocationDTO location;
    @JsonProperty("fcm_token")
    private String fcmToken;

    @JsonProperty("is_in_trip")
    private boolean isInTrip;

}

