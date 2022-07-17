package duynn.gotogether.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
//@PrimaryKeyJoinColumn(name = "userId")
public class Client extends User implements Serializable {
    private static final long serialVersionUID = 5L;

    @Id
    @SequenceGenerator(name = "client_seq", sequenceName = "client_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "client_seq")
    private Long id;

    @JoinColumn(name = "position_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Position position;

    @Column(name = "rate", columnDefinition = "double default 0")
    private Double rate;

    @Column(name = "is_in_trip", columnDefinition = "boolean default false")
    private boolean isInTrip;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade=CascadeType.ALL)
    private List<Transport> transports;
}
