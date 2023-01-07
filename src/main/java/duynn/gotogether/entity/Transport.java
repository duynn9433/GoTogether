package duynn.gotogether.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","$$_hibernate_interceptor"})
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
    @JsonProperty("license_plate")
    private String licensePlate;

    @Column(name = "desciption")
    private String description;

    @Column(name = "image")
    private String image;
    @JsonProperty("transport_type")
    @Column(name = "transport_type", nullable = false)
//    @Enumerated(EnumType.STRING)
    @Convert(converter = TransportTypeConverter.class)
    private TransportType transportType;
    @JsonProperty("owner")
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client owner;


}
