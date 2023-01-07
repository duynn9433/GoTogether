
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
public class PlaceDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    @JsonProperty("place_id")
    private String placeID;
    @JsonProperty("formatted_address")
    private String formattedAddress;
    @JsonProperty("name")
    private String name;
    @JsonProperty("lat")
    private double lat;
    @JsonProperty("lng")
    private double lng;



}
