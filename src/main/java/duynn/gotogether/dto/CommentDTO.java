package duynn.gotogether.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO implements Serializable {
    private static final long serialVersionUID = 11L;

    private Long id;

    private String content;

    private Integer rating;

    @JsonProperty("author")
    private ClientDTO Author;

    @JsonProperty("receiver")
    private ClientDTO Receiver;

    private TripDTO trip;
}
