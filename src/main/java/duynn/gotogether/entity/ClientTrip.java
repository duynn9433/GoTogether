package duynn.gotogether.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Data
@Table(name = "trip")
public class ClientTrip implements Serializable {
    private static final long serialVersionUID = 10L;

    @Id
    @SequenceGenerator(name = "trip_seq", sequenceName = "trip_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "trip_seq")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickup_position_id", referencedColumnName = "id")
    private Position pickUpPosition;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dropoff_position_id", referencedColumnName = "id")
    private Position dropOffPosition;

    @Column(name = "pickup_time")
    private Date pickUpTime;

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

}
