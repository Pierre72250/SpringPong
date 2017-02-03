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
@Table(name = "Competition")
public class Competition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @Getter @Setter private long id;

    @Column(name="name")
    @Size(min = 2, max = 50)
    @Getter @Setter private String name;

    @Column(name="joinCode")
    @Size(min = 2, max = 50)
    @Getter @Setter private String joinCode;

    public Competition() {}

    public Competition(String name, String joinCode)  {
        this.name = name;
        this.joinCode = joinCode;
    }
}
