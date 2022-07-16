package duynn.gotogether.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Data
@Table(name = "trip")
public class Trip implements Serializable {
    private static final long serialVersionUID = 9L;

    @Id
    @SequenceGenerator(name = "trip_seq", sequenceName = "trip_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "trip_seq")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_position_id", referencedColumnName = "id")
    private Position startPosition;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_position_id", referencedColumnName = "id")
    private Position endPosition;

    @Column(name = "start_time")
    private Date startTime;

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
    private Client Driver;

}
