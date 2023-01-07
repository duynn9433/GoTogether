package duynn.gotogether.dto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import duynn.gotogether.dto.entity_dto.ClientDTO;
import duynn.gotogether.entity.Client;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private String jwttoken;
    private Client client;
}