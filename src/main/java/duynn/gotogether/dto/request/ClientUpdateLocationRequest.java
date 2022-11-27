package duynn.gotogether.dto.request;

import duynn.gotogether.dto.entity_dto.LocationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientUpdateLocationRequest implements Serializable {
    public static final long serialVersionUID = 1L;

    private LocationDTO location;
    private Long tripId;
    private Long clientId;
}
