package duynn.gotogether.dto.entity_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Calendar;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientTripDTO implements Serializable {
    private static final long serialVersionUID = 10L;

    private Long id;

    @JsonProperty("pickup_place")
    private PlaceDTO pickUpPlace;

    @JsonProperty("dropoff_place")
    private PlaceDTO dropOffPlace;

    @JsonProperty("pickup_time")
    private Calendar pickUpTime;

    @JsonProperty("number_of_people")
    private Integer numOfPeople;

    @JsonProperty("price_per_km")
    private Double pricePerKmForOnePeople;

    @JsonProperty("is_accepted")
    private boolean isAccepted;

    @JsonProperty("is_canceled")
    private boolean isCanceled;

    private ClientDTO client;

    private TripDTO trip;

    @JsonProperty("distance")
    private Double distance;

}
