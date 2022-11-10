package duynn.gotogether.entity;

import com.google.gson.annotations.SerializedName;
import duynn.gotogether.util.enumClass.TransportType;
import duynn.gotogether.util.enumClass.TransportTypeConverter;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Embeddable
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transport")
public class Transport implements Serializable {
    private static final long serialVersionUID = 7L;

    @Id
    @SequenceGenerator(name = "transport_seq", sequenceName = "transport_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "transport_seq")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "license_plate", nullable = false)
    @SerializedName("license_plate")
    private String licensePlate;

    @Column(name = "desciption")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "transport_type", nullable = false)
//    @Enumerated(EnumType.STRING)
    @Convert(converter = TransportTypeConverter.class)
    private TransportType transportType;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client owner;


}
