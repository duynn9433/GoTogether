package duynn.gotogether.dto.response;

import duynn.gotogether.dto.entity_dto.TripDTO;
import lombok.Data;

@Data
public class TripResponse {
    public static final Long serialVersionUID = 1L;

    private TripDTO trip;
    private String status;
    private String message;
}
