package duynn.gotogether.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PositionDTO implements Serializable {

    private static final long serialVersionUID = 6L;

    private Long id;

    private Double latitude;

    private Double longitude;

}