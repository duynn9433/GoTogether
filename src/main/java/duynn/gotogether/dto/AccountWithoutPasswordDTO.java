package duynn.gotogether.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountWithoutPasswordDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    @JsonIgnore
    private String password;
}
