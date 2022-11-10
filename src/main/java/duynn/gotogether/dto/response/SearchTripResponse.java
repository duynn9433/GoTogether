package duynn.gotogether.dto.response;

import duynn.gotogether.dto.TripDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchTripResponse {
    private static final long serialVersionUID = 1L;
    private List<TripDTO> trips;
    private String status;
    private String message;
}
