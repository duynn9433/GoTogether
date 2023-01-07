package duynn.gotogether.dto.entity_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO extends UserDTO implements Serializable {
    private static final long serialVersionUID = 5L;

    private Double rate;

    @JsonProperty("is_in_trip")
    private boolean isInTrip;

    @JsonProperty("fcm_token")
    private String fcmToken;
    @JsonProperty("lat")
    private double lat;
    @JsonProperty("lng")
    private double lng;

//    @JsonManagedReference
    private List<TransportWithoutOwnerDTO> transports;

}
