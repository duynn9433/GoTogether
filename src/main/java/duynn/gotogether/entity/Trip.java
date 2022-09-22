package duynn.gotogether.entity;

import com.google.gson.annotations.SerializedName;
import duynn.gotogether.entity.place.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_position_id", referencedColumnName = "id")
    private Position startPosition;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_place_id", referencedColumnName = "id")
    @SerializedName("start_place")
    private Place startPlace;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_position_id", referencedColumnName = "id")
    private Position endPosition;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_place_id", referencedColumnName = "id")
    @SerializedName("end_place")
    private Place endPlace;

    @ElementCollection
    @CollectionTable(name = "stop_place", joinColumns = @JoinColumn(name = "trip_id"))
    @SerializedName("stop_places")
    private List<Place> listStopPlace;

    @Column(name = "start_time")
    @SerializedName("start_time")
    private Date startTime;

    @Column(name = "total_seat", nullable = false)
    @SerializedName("total_seat")
    private Integer totalSeat;

    @Column(name = "empty_seat", nullable = false)
    @SerializedName("empty_seat")
    private Integer emptySeat;

    @Column(name = "price_per_km", nullable = false)
    @SerializedName("price_per_km")
    private Double pricePerKm;

    @Column(name = "description")
    @SerializedName("description")
    private String description;

    @Column(name = "distance_plus", nullable = false)
    @SerializedName("distance_plus")
    private Double distancePlus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_id", referencedColumnName = "id")
    @SerializedName("transport")
    private Transport transport;

    @Column(name = "is_finished", nullable = false)
    @SerializedName("is_finished")
    private boolean isFinished;

    @Column(name = "is_started", nullable = false)
    @SerializedName("is_started")
    private boolean isStarted;

    @Column(name = "is_canceled", nullable = false)
    @SerializedName("is_canceled")
    private boolean isCanceled;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    @SerializedName("driver")
    private Client Driver;

}
