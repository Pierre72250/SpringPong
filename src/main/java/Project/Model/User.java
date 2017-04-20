package Project.Model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

    @Column(name="dateOfBirth")
    @Getter @Setter private Date dateOfBirth;

    @Column(name="mail")
    @Size(min = 2, max = 50)
    @Getter @Setter private String mail;

    @Column(name="password")
    @Size(min = 2, max = 255)
    @Getter @Setter private String password;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(cascade=CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "user")
    @Getter @Setter private List<Participation> participations;

    public User() {this.participations = new ArrayList<Participation>();}

    public User(String name, String surname, Date dateOfBirth, String mail, String password)  {
        this();
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.mail = mail;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
