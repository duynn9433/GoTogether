package duynn.gotogether.entity;

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
//@PrimaryKeyJoinColumn(name = "userId")
public class Client extends User implements Serializable {
    private static final long serialVersionUID = 5L;

//    @Id
//    @SequenceGenerator(name = "client_seq", sequenceName = "client_seq", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "client_seq")
//    private Long id;

    @JoinColumn(name = "location_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Location location;

    @Column(name = "rate", columnDefinition = "double default 0")
    private Double rate;

    @Column(name = "is_in_trip", columnDefinition = "boolean default false")
    private boolean isInTrip;

//    @ElementCollection
//    @CollectionTable(name = "transport", joinColumns = @JoinColumn(name = "owner_id"))
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @OneToMany(mappedBy = "owner")
    private List<Transport> transports;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + getId() + ", " +
                "rate = " + getRate() + ", " +
                "isInTrip = " + isInTrip() + ", " +
                "role = " + getRole() + ", " +
                "avatar = " + getAvatar() + ", " +
                "description = " + getDescription() + ", " +
                "isActive = " + isActive() + ")";
    }
}
