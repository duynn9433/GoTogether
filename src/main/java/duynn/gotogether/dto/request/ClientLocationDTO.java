package duynn.gotogether.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import duynn.gotogether.dto.entity_dto.TransportWithoutOwnerDTO;
import duynn.gotogether.dto.entity_dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"role","avatar","description","account","fullname","contactInformation","address","isActive"})
public class ClientLocationDTO extends UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty("lat")
    private double lat;
    @JsonProperty("lng")
    private double lng;

    @Override
    public String toString() {
        return "ClientLocationDTO{" +
                "id=" +getId() +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
