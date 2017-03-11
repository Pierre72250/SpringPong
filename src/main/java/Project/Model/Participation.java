package Project.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Participation")
public class Participation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @Getter @Setter private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser", nullable = false)
    @Getter @Setter private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCompetition", nullable = false)
    @Getter @Setter private Competition competition;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "participation")
    @Getter @Setter private List<Elo> elos;

    @Column(name="state")
    @Getter @Setter private Boolean state;

    public Participation() {
        this.elos = new ArrayList<Elo>();
    }

    public Participation(User user, Competition competition, Boolean state)  {
        this();
        this.user = user;
        this.competition = competition;
        this.state = state;
    }
}
