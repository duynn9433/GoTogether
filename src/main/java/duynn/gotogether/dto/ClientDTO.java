package duynn.gotogether.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private LocationDTO location;

    private Double rate;

    @JsonProperty("is_in_trip")
    private boolean isInTrip;

//    @JsonManagedReference
    private List<TransportWithoutOwnerDTO> transports;

    @Override
    public String toString() {
        String s = super.toString() + "ClientDTO{" +
                ", location=" + location +
                ", rate=" + rate +
                ", isInTrip=" + isInTrip +
                ", transports=" + transports +
                '}';
        return s;
    }
}
