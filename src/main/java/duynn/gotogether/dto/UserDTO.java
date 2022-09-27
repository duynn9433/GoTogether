package duynn.gotogether.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    private String role;

    private String avatar;

    private String description;

    private AccountDTO account;

    private FullnameDTO fullname;

    @JsonProperty("contact_information")
    private ContactInfomationDTO contactInfomation;

    private AddressDTO address;

    @JsonIgnore
    private boolean isActive;

//    public String getFullname() {
//        return fullname.getFirstName() +" "+ fullname.getMiddleName() +" " + fullname.getLastName();
//    }

}
