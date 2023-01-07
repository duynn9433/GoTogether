
package duynn.gotogether.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import duynn.gotogether.entity.place.Geometry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "place")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","$$_hibernate_interceptor"})
public class Place implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "place_seq", sequenceName = "place_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "place_seq")
    private Long id;
    @JsonProperty("place_id")
    @Column(name = "place_id", nullable = false)
    private String placeID;
    @JsonProperty("formatted_address")
    @Column(name = "formatted_address", nullable = false)
    private String formattedAddress;
    @JsonProperty("name")
    @Column(name = "name", nullable = false)
    private String name;
    @JsonProperty("lat")
    @Column(name = "lat", nullable = false)
    private double lat;
    @JsonProperty("lng")
    @Column(name = "lng", nullable = false)
    private double lng;

}
