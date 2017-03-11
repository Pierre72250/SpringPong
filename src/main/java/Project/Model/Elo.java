package Project.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Elo")
public class Elo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @Getter @Setter private long id;

    @Column(name="elo")
    @Getter @Setter private int elo;

    @Column(name="date")
    @Getter @Setter private Date date;

    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "idParticipation", nullable = false)
    @Getter @Setter private Participation participation;

    public Elo() {}

    public Elo(int elo, Participation participation)  {
        this.elo = elo;
        this.participation = participation;
    }
}
