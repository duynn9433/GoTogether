package duynn.gotogether.dto.entity_dto;

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
public class LocationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @JsonProperty("lat")
    private double lat;

    @JsonProperty("lng")
    private double lng;

}

