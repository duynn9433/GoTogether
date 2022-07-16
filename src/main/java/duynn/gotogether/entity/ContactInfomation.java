package duynn.gotogether.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "contact_infomation")
public class ContactInfomation implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @SequenceGenerator(name = "contact_infomation_seq", sequenceName = "contact_infomation_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "contact_infomation_seq")
    private Long id;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email")
    private String email;


}
