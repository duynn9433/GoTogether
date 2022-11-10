package duynn.gotogether.dto.request;

import duynn.gotogether.entity.place.Place;
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

    private Place startPlace;
    private Place endPlace;
    private Calendar startTime;
    private Integer numOfSeat;
}
