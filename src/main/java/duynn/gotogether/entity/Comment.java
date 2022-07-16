package duynn.gotogether.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "comment")
public class Comment implements Serializable {
    private static final long serialVersionUID = 11L;

    @Id
    @SequenceGenerator(name = "comment_seq", sequenceName = "comment_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "comment_seq")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Client Author;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private Client Receiver;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private Trip trip;
}