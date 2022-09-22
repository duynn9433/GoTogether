package duynn.gotogether.entity;

import com.google.gson.annotations.SerializedName;
import duynn.gotogether.util.enumClass.TransportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
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
    @Enumerated(EnumType.STRING)
    @SerializedName("transport_type")
    private TransportType transportType;

//    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @SerializedName("owner")
    private Client owner;

}
