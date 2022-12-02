package duynn.gotogether.dto.response;

import duynn.gotogether.dto.entity_dto.ClientTripDTO;
import duynn.gotogether.dto.entity_dto.TripDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcceptedTripResponse {
    public static final Long serialVersionUID = 1L;

    private TripDTO trip;
    private ClientTripDTO clientTrip;
    private String status;
    private String message;
}
