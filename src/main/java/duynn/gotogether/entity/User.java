package duynn.gotogether.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","$$_hibernate_interceptor"})
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
    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "account_id", nullable = false, referencedColumnName = "id")
    private Account account;

    @JsonProperty("fullname")
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "fullname_id", nullable = false, referencedColumnName = "id")
    private Fullname fullname;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "contact_information_id", nullable = false, referencedColumnName = "id")
    @JsonProperty("contact_information")
    private ContactInfomation contactInformation;
    @JsonProperty("address")
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false, referencedColumnName = "id")
    private Address address;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

//    public String getFullname() {
//        return fullname.getFirstName() +" "+ fullname.getMiddleName() +" " + fullname.getLastName();
//    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setFullname(Fullname fullname) {
        this.fullname = fullname;
    }

    public void setContactInformation(ContactInfomation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getDescription() {
        return description;
    }

    public Account getAccount() {
        return account;
    }

    public Fullname getFullname() {
        return fullname;
    }

    public ContactInfomation getContactInformation() {
        return contactInformation;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isActive() {
        return isActive;
    }
}
