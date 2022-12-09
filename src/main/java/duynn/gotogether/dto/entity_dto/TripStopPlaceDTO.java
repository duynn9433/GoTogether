package duynn.gotogether.dto.entity_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import duynn.gotogether.entity.Trip;
import duynn.gotogether.entity.place.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripStopPlaceDTO implements Serializable {
    private static final long serialVersionUID = 4L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("position")
    private Integer position;

    @JsonProperty("place")
    private PlaceDTO place;
}
