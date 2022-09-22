
package duynn.gotogether.entity.place;

import com.google.gson.annotations.SerializedName;
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
public class Place implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "place_seq", sequenceName = "place_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "place_seq")
    private Long id;

    @SerializedName("place_id")
    @Column(name = "place_id", nullable = false)
    private String placeID;

    @SerializedName("formatted_address")
    @Column(name = "formatted_address", nullable = false)
    private String formattedAddress;

    @SerializedName("geometry")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "geometry_id", referencedColumnName = "id")
    private Geometry geometry;

    @SerializedName("name")
    @Column(name = "name", nullable = false)
    private String name;

}
