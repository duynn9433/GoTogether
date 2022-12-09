package duynn.gotogether.entity;

import duynn.gotogether.entity.place.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trip")
public class Trip implements Serializable {
    private static final long serialVersionUID = 9L;

    @Id
    @SequenceGenerator(name = "trip_seq", sequenceName = "trip_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "trip_seq")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "start_place_id", referencedColumnName = "id")
    private Place startPlace;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "end_place_id", referencedColumnName = "id")
    private Place endPlace;

//    @ElementCollection()
//    @CollectionTable(name = "stop_place", joinColumns = @JoinColumn(name = "trip_id"))
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "trip")
    @OrderBy("position ASC")
    private List<TripStopPlace> listStopPlace;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar startTime;

    @Column(name = "total_seat", nullable = false)
    private Integer totalSeat;

    @Column(name = "empty_seat", nullable = false)
    private Integer emptySeat;

    @Column(name = "price_per_km", nullable = false)
    private Double pricePerKm;

    @Column(name = "description")
    private String description;

    @Column(name = "distance_plus", nullable = false)
    private Double distancePlus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_id", referencedColumnName = "id")
    private Transport transport;

    @Column(name = "is_finished", nullable = false)
    private boolean isFinished;

    @Column(name = "is_started", nullable = false)
    private boolean isStarted;

    @Column(name = "is_canceled", nullable = false)
    private boolean isCanceled;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private Client driver;

    @Column(name = "fcm_topic")
    private String fcmTopic;

}
