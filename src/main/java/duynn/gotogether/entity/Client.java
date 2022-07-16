package duynn.gotogether.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table(name = "client")
public class Client extends User implements Serializable {
    private static final long serialVersionUID = 5L;

    @Id
    @SequenceGenerator(name = "client_seq", sequenceName = "client_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "client_seq")
    private Long id;

    @JoinColumn(name = "position_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    private Position position;

    @Column(name = "rate", nullable = false)
    private int rate;

    @Column(name = "is_in_trip", nullable = false)
    private boolean isInTrip;


}
