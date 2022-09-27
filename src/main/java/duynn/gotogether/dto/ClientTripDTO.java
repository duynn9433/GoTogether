package duynn.gotogether.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientTripDTO implements Serializable {
    private static final long serialVersionUID = 10L;

    private Long id;


    private PositionDTO pickUpPosition;

    @JsonProperty("pickup_place")
    private PlaceDTO pickUpPlace;

    private PositionDTO dropOffPosition;

    @JsonProperty("dropoff_place")
    private PlaceDTO dropOffplace;

    @JsonProperty("pickup_time")
    private Date pickUpTime;

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

}
