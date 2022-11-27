package duynn.gotogether.dto.client_trip_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import duynn.gotogether.dto.entity_dto.ContactInfomationDTO;
import duynn.gotogether.dto.entity_dto.FullnameDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 4L;

    private Long id;

    private String avatar;

    private FullnameDTO fullname;

    @JsonProperty("contact_information")
    private ContactInfomationDTO contactInfomation;

}
