package duynn.gotogether.dto.request;

import duynn.gotogether.dto.entity_dto.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassengerFinishRequest implements Serializable {
    public static final long serialVersionUID = 10L;
    private CommentDTO comment;
}