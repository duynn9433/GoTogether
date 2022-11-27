package duynn.gotogether.dto.entity_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import duynn.gotogether.entity.Client;
import duynn.gotogether.entity.ClientTrip;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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

    @JsonProperty("driver")
    private Client driver;

    @JsonProperty("client_trip")
    private ClientTrip clientTrip;
}
