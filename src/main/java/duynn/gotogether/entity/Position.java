package duynn.gotogether.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "position")
public class Position implements Serializable {

    private static final long serialVersionUID = 6L;
    @Id
    @SequenceGenerator(name = "position_seq", sequenceName = "position_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "position_seq")
    private Long id;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

}