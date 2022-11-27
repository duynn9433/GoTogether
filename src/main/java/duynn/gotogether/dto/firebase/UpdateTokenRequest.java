package duynn.gotogether.dto.firebase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTokenRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String token;
    private Long clientId;
}
