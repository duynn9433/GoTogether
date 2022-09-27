package duynn.gotogether.entity.place;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "geometry")
public class Geometry implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "geometry_seq", sequenceName = "geometry_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "geometry_seq")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @Override
    public String toString() {
        return "Geometry{" +
                "id=" + id +
                ", location=" + location +
                '}';
    }
}
