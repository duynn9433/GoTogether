package duynn.gotogether.entity;

import duynn.gotogether.entity.place.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trip_stop_place")
public class TripStopPlace implements Serializable {
    private static final long serialVersionUID = 4L;

    @Id
    @SequenceGenerator(name = "trip_stop_place_seq", sequenceName = "trip_stop_place_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "trip_stop_place_seq")
    private Long id;

    @Column(name = "position", nullable = false)
    private Integer position;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "place_id", nullable = false,referencedColumnName = "id")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Trip trip;
}
