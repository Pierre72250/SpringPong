package Project.DAO;


import Project.Model.Resultat;
import Project.Model.User;

public interface ResultatDAO {

    public Long add(Resultat resultat);

    public Long getTotalMatchs(User user);

    public Long getTotalVictories(User user);

    public Long getTotalLooses(User user);

}
