package duynn.gotogether.entity;

import com.google.gson.annotations.SerializedName;
import duynn.gotogether.entity.place.Place;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client_trip")
public class ClientTrip implements Serializable {
    private static final long serialVersionUID = 10L;

    @Id
    @SequenceGenerator(name = "trip_seq", sequenceName = "trip_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "trip_seq")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pickup_place_id", referencedColumnName = "id")
    private Place pickUpPlace;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dropoff_place_id", referencedColumnName = "id")
    private Place dropOffPlace;

    @Column(name = "pickup_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar pickUpTime;

    @Column(name = "number_of_people", nullable = false)
    private Integer numOfPeople;

    @Column(name = "price_per_km", nullable = false)
    private Double pricePerKmForOnePeople;

    @Column(name = "is_accepted", nullable = false)
    private boolean isAccepted;

    @Column(name = "is_canceled", nullable = false)
    private boolean isCanceled;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private Trip trip;

    @Column(name = "distance")
    private Double distance;

}
