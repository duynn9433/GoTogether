package duynn.gotogether.dto.request;

import duynn.gotogether.dto.entity_dto.CommentDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private static final long serialVersionUID = 1L;
    private CommentDTO commentDTO;
}
