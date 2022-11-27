package duynn.gotogether.dto.entity_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfomationDTO implements Serializable {
    private static final long serialVersionUID = 2L;

    private Long id;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String email;
}
