package Project.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "Elo")
public class Elo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @Getter @Setter private long id;

    @Column(name="elo", nullable = false)
    @Getter @Setter private int elo;

    @Column(name="date", nullable = false)
    @Getter @Setter private Date date;

    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "idParticipation", nullable = false)
    @Getter @Setter private Participation participation;

    public Elo() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        this.date = currentTimestamp;
    }

    public Elo(int elo, Participation participation)  {
        this();
        this.elo = elo;
        this.participation = participation;
    }
}
