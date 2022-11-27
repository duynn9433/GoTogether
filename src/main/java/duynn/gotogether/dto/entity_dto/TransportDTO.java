package duynn.gotogether.dto.entity_dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import duynn.gotogether.util.enumClass.TransportType;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransportDTO implements Serializable {
    private static final long serialVersionUID = 7L;

    private Long id;

    private String name;

    @JsonProperty("license_plate")
    private String licensePlate;

    private String description;

    private String image;

    @JsonProperty("transport_type")
    private TransportType transportType;

    @JsonProperty("owner")
//    @JsonBackReference
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ClientDTO.class)
    private ClientDTO owner;

}
