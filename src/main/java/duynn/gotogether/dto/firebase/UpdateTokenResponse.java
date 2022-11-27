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
public class UpdateTokenResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String message;
    private String status;
}
