package duynn.gotogether.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","$$_hibernate_interceptor"})
public class ClientTrip implements Serializable {
    private static final long serialVersionUID = 10L;

    @Id
    @SequenceGenerator(name = "trip_seq", sequenceName = "trip_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "trip_seq")
    private Long id;
    @JsonProperty("pickup_place")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pickup_place_id", referencedColumnName = "id")
    private Place pickUpPlace;
    @JsonProperty("dropoff_place")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dropoff_place_id", referencedColumnName = "id")
    private Place dropOffPlace;
    @JsonProperty("pickup_time")
    @Column(name = "pickup_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar pickUpTime;
    @JsonProperty("number_of_people")
    @Column(name = "number_of_people", nullable = false)
    private Integer numOfPeople;
    @JsonProperty("price_per_km")
    @Column(name = "price_per_km", nullable = false)
    private Double pricePerKmForOnePeople;
    @JsonProperty("is_accepted")
    @Column(name = "is_accepted", nullable = false)
    private boolean isAccepted;
    @JsonProperty("is_canceled")
    @Column(name = "is_canceled", nullable = false)
    private boolean isCanceled;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private Trip trip;
    @JsonProperty("distance")
    @Column(name = "distance")
    private Double distance;
    @JsonProperty("is_driver_commentted")
    @Column(name = "is_driver_commentted")
    private Boolean isDriverCommentted;
    @JsonProperty("is_passenger_commentted")
    @Column(name = "is_passenger_commentted")
    private Boolean isPassengerCommentted;


}
