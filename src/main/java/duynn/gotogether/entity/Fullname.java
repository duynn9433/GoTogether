package duynn.gotogether.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fullname")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","$$_hibernate_interceptor"})
public class Fullname implements Serializable {
    private static final long serialVersionUID = 4L;

    @Id
    @SequenceGenerator(name = "fullname_seq", sequenceName = "fullname_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "fullname_seq")
    private Long id;
    @JsonProperty("first_name")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @JsonProperty("last_name")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @JsonProperty("middle_name")
    @Column(name = "middle_name")
    private String middleName;
    @JsonProperty("nick_name")
    @Column(name = "nick_name")
    private String nickName;
}
