package duynn.gotogether.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 4L;

    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "user_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "description", nullable = false)
    private String description;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "account_id", nullable = false, referencedColumnName = "id")
    private Account account;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "fullname_id", nullable = false, referencedColumnName = "id")
    private Fullname fullname;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "contact_infomation_id", nullable = false, referencedColumnName = "id")
    private ContactInfomation contactInfomation;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false, referencedColumnName = "id")
    private Address address;

    @Column(name = "is_active", nullable = false)
    @JsonIgnore
    private boolean isActive;

    public String getFullname() {
        return fullname.getFirstName() +" "+ fullname.getMiddleName() +" " + fullname.getLastName();
    }

}
