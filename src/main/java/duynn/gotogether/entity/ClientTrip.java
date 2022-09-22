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
    @JoinColumn(name = "pickup_place_id", referencedColumnName = "id")
    @SerializedName("pickup_place")
    private Place pickUpPlace;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dropoff_position_id", referencedColumnName = "id")
    private Position dropOffPosition;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dropoff_place_id", referencedColumnName = "id")
    @SerializedName("dropoff_place")
    private Place dropOffplace;

    @Column(name = "pickup_time")
    @SerializedName("pickup_time")
    private Date pickUpTime;

    @Column(name = "number_of_people", nullable = false)
    @SerializedName("number_of_people")
    private Integer numOfPeople;

    @Column(name = "price_per_km", nullable = false)
    @SerializedName("price_per_km")
    private Double pricePerKmForOnePeople;

    @Column(name = "is_accepted", nullable = false)
    @SerializedName("is_accepted")
    private boolean isAccepted;

    @Column(name = "is_canceled", nullable = false)
    @SerializedName("is_canceled")
    private boolean isCanceled;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private Trip trip;

}
