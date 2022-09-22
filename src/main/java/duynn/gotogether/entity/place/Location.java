package duynn.gotogether.entity.place;

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
@Table(name = "location")
public class Location implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "location_seq", sequenceName = "location_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "location_seq")
    private Long id;

    @Column(name = "lat", nullable = false)
    @SerializedName("lat")
    private double lat;

    @Column(name = "lng", nullable = false)
    @SerializedName("lng")
    private double lng;

}

