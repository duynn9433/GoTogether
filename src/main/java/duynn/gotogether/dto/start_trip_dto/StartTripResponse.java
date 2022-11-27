package duynn.gotogether.dto.start_trip_dto;

import duynn.gotogether.dto.entity_dto.TripDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StartTripResponse {
    public static final Long serialVersionUID = 1L;
    private TripDTO trip;
    private List<ClientTripDTO> clientTrips;
    private String status;
    private String message;
}
