package duynn.gotogether.dto.response;

import duynn.gotogether.dto.entity_dto.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListCommentResponse {
    private static final long serialVersionUID = 1L;
    private List<CommentDTO> commentDTOS;
    private String status;
    private String message;
}
