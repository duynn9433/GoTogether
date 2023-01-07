package duynn.gotogether.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import duynn.gotogether.entity.place.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","$$_hibernate_interceptor"})
//@PrimaryKeyJoinColumn(name = "userId")
public class Client extends User implements Serializable {
    private static final long serialVersionUID = 5L;

    @Column(name = "rate", columnDefinition = "double default 0")
    private Double rate;
    @JsonProperty("is_in_trip")
    @Column(name = "is_in_trip", columnDefinition = "boolean default false")
    private boolean isInTrip;
    @JsonProperty("fcm_token")
    @Column(name = "fcm_token", columnDefinition = "string default ''")
    private String fcmToken;
    @JsonProperty("lat")
    @Column(name = "lat", columnDefinition = "double default 0")
    private double lat;
    @JsonProperty("lng")
    @Column(name = "lng", columnDefinition = "double default 0")
    private double lng;

//    @ElementCollection
//    @CollectionTable(name = "transport", joinColumns = @JoinColumn(name = "owner_id"))
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @OneToMany(mappedBy = "owner")
    private List<Transport> transports;

    public String printLocation(){
        return "(" + lat + ", " + lng + ")";
    }

}
