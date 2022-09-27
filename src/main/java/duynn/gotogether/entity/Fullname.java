package duynn.gotogether.entity;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fullname")
public class Fullname implements Serializable {
    private static final long serialVersionUID = 4L;

    @Id
    @SequenceGenerator(name = "fullname_seq", sequenceName = "fullname_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "fullname_seq")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "nick_name")
    private String nickName;
}
