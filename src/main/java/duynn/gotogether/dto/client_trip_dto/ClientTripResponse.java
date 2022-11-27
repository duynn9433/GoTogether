package duynn.gotogether.dto.client_trip_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientTripResponse {
    private static final long serialVersionUID = 1L;

    private String message;
    private String status;
    private ClientTripDTO data;
}
