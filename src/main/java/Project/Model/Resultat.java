package Project.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Resultat")
public class Resultat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @Getter @Setter private long id;

    @Column(name="date")
    @Getter @Setter private Date date;

    @Column(name="scoreUser_1")
    @Getter @Setter private int scoreUser_1;

    @Column(name="scoreUser_2")
    @Getter @Setter private int scoreUser_2;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCompetition", nullable = false)
    @Getter @Setter private Competition competition;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser_1", nullable = false)
    @Getter @Setter private User user_1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser_2", nullable = false)
    @Getter @Setter private User user_2;


    public Resultat() {}

    public Resultat(int scoreUser_1, int scoreUser_2, Competition competition, User user_1, User user_2) {
        this.scoreUser_1 = scoreUser_1;
        this.scoreUser_2 = scoreUser_2;
        this.competition = competition;
        this.user_1 = user_1;
        this.user_2 = user_2;
    }

    @Override
    public String toString() {
        return "Resultat{" +
                "id=" + id +
                ", date=" + date +
                ", scoreUser_1=" + scoreUser_1 +
                ", scoreUser_2=" + scoreUser_2 +
                ", competition=" + competition +
                ", user_1=" + user_1 +
                ", user_2=" + user_2 +
                '}';
    }
}
