package duynn.gotogether.dto.entity_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO implements Serializable {
    private static final long serialVersionUID = 3L;

    private Long id;
    private String city;
    private String district;
    private String province;
    private String detail;
}
