package duynn.gotogether.entity;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import duynn.gotogether.entity.place.GoongPlace;
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
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","$$_hibernate_interceptor"})
public class Trip implements Serializable {
    private static final long serialVersionUID = 9L;
    @Id
    @SequenceGenerator(name = "trip_seq", sequenceName = "trip_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "trip_seq")
    private Long id;
    @JsonProperty("start_place")
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "start_place_id", referencedColumnName = "id")
    private Place startPlace;
    @JsonProperty("end_place")
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "end_place_id", referencedColumnName = "id")
    private Place endPlace;

//    @ElementCollection()
//    @CollectionTable(name = "stop_place", joinColumns = @JoinColumn(name = "trip_id"))
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty("stop_places")
    @OneToMany(mappedBy = "trip")
    @OrderBy("position ASC")
    private List<TripStopPlace> listStopPlace;
    @JsonProperty("start_time")
    @JsonSerialize(using = CalendarSerializer.class)
    @JsonDeserialize(using = DateDeserializers.CalendarDeserializer.class)
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar startTime;
    @JsonProperty("total_seat")
    @Column(name = "total_seat", nullable = false)
    private Integer totalSeat;
    @JsonProperty("empty_seat")
    @Column(name = "empty_seat", nullable = false)
    private Integer emptySeat;
    @JsonProperty("price_per_km")
    @Column(name = "price_per_km", nullable = false)
    private Double pricePerKm;
    @JsonProperty("description")
    @Column(name = "description")
    private String description;
    @JsonProperty("distance_plus")
    @Column(name = "distance_plus", nullable = false)
    private Double distancePlus;
    @JsonProperty("transport")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_id", referencedColumnName = "id")
    private Transport transport;
    @JsonProperty("is_finished")
    @Column(name = "is_finished", nullable = false)
    private boolean isFinished;
    @JsonProperty("is_started")
    @Column(name = "is_started", nullable = false)
    private boolean isStarted;
    @JsonProperty("is_canceled")
    @Column(name = "is_canceled", nullable = false)
    private boolean isCanceled;
    @JsonProperty("driver")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private Client driver;
    @JsonProperty("fcm_topic")
    @Column(name = "fcm_topic")
    private String fcmTopic;

}
