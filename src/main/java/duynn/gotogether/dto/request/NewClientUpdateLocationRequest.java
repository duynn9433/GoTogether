package duynn.gotogether.dto.request;

import duynn.gotogether.dto.entity_dto.LocationDTO;
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
public class NewClientUpdateLocationRequest implements Serializable {
    public static final long serialVersionUID = 1L;
    private LocationDTO location;
    //for driver request
    private List<Long> clientLocationIDs;
    //for client request
    private Long driverId;
}
