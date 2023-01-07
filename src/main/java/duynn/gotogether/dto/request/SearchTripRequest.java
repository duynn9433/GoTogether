package duynn.gotogether.dto.request;

import duynn.gotogether.dto.entity_dto.PlaceDTO;
import duynn.gotogether.entity.Place;
import duynn.gotogether.entity.place.GoongPlace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchTripRequest {
    private static final long serialVersionUID = 1L;

    private PlaceDTO startPlace;
    private PlaceDTO endPlace;
    private Calendar startTime;
    private Integer numOfSeat;
}
