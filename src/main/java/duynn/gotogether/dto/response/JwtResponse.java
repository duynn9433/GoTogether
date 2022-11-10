package duynn.gotogether.dto.response;

import duynn.gotogether.dto.ClientDTO;
import duynn.gotogether.dto.UserDTO;
import duynn.gotogether.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private String jwttoken;
    private ClientDTO client;
}