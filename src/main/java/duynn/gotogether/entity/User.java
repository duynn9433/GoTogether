package duynn.gotogether.entity;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 4L;

    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "user_seq")
    private Long id;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "description", nullable = false)
    private String description;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false, referencedColumnName = "id")
    private Account account;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fullname_id", nullable = false, referencedColumnName = "id")
    private Fullname fullname;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_infomation_id", nullable = false, referencedColumnName = "id")
    private ContactInfomation contactInfomation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false, referencedColumnName = "id")
    private Address address;

    @Column(name = "is_active", nullable = false)
    @JsonIgnore
    private boolean isActive;

    public String getFullname() {
        return fullname.getFirstName() +" "+ fullname.getMiddleName() +" " + fullname.getLastName();
    }

}
