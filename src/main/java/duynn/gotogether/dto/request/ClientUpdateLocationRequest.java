package duynn.gotogether.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientUpdateLocationRequest implements Serializable {
    public static final long serialVersionUID = 1L;
    private ClientLocationDTO location;
    //for driver request
    private List<Long> passengerIDs;
    //for passenger request
    private Long driverId;
}
