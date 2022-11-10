package duynn.gotogether.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripDTO implements Serializable {
    private static final long serialVersionUID = 9L;

    private Long id;

    private PositionDTO startPosition;

    @JsonProperty("start_place")
    private PlaceDTO startPlace;

    private PositionDTO endPosition;
    @JsonProperty("end_place")
    private PlaceDTO endPlace;

    @JsonProperty("stop_places")
    private List<PlaceDTO> listStopPlace;

    @JsonProperty("start_time")
    @JsonSerialize(using = CalendarSerializer.class)
    @JsonDeserialize(using = DateDeserializers.CalendarDeserializer.class)
    private Calendar startTime;

    @JsonProperty("total_seat")
    private Integer totalSeat;

    @JsonProperty("empty_seat")
    private Integer emptySeat;

    @JsonProperty("price_per_km")
    private Double pricePerKm;

    @JsonProperty("description")
    private String description;

    @JsonProperty("distance_plus")
    private Double distancePlus;

    @JsonProperty("transport")
    private TransportWithoutOwnerDTO transport;

    @JsonProperty("is_finished")
    private boolean isFinished;

    @JsonProperty("is_started")
    private boolean isStarted;

    @JsonProperty("is_canceled")
    private boolean isCanceled;

    @JsonProperty("driver")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private ClientDTO driver;

}
