package duynn.gotogether.dto.response;

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
public class ListLocationResponse implements Serializable {
    public static final long serialVersionUID = 1L;

    private String message;
    private String status;
    private List<LocationDTO> locationList;

}
