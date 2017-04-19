package Project.Model;

import com.sun.org.apache.regexp.internal.RE;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    @Column(name="description")
    @Getter @Setter private String description;

    @Column(name="creation", nullable = false)
    @Getter @Setter private Date creation;

    @Column(name="state")
    @Getter @Setter private Boolean state;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(cascade=CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "competition")
    @Getter @Setter private List<Participation> participations;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(cascade=CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "competition")
    @Getter @Setter private List<Resultat> resultats;

    public Competition() {
        this.participations = new ArrayList<Participation>();
        this.resultats = new ArrayList<Resultat>();
        Calendar calendar = Calendar.getInstance();
        java.sql.Date dateCreation = new java.sql.Date(calendar.getTime().getTime());
        this.creation = dateCreation;
    }

    public Competition(String name, String description, Boolean state)  {
        this();
        this.name = name;
        this.description = description;
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Competition that = (Competition) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
