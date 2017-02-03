package Project.Model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "User")
public class User implements Serializable {

    private static final long serialVersionUID = 368875986784459499L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @Getter @Setter private long id;

    @Column(name="name")
    @Size(min = 2, max = 50)
    @Getter @Setter private String name;

    @Column(name="surname")
    @Size(min = 2, max = 50)
    @Getter @Setter private String surname;

    @Column(name="mail")
    @Size(min = 2, max = 50)
    @Getter @Setter private String mail;

    @Column(name="password")
    @Size(min = 2, max = 255)
    @Getter @Setter private String password;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(cascade=CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "user")
    @Getter @Setter private List<Message> messages;

    public User() {}

    public User(String name, String surname, String mail, String password, int elo)  {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
    }
}
