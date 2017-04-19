package Project.Service;

import Project.Model.Resultat;
import Project.Model.User;

public interface ResulatService {

    public long add(Resultat resultat);

    public Long getTotalMatchs(User user);

    public Long getTotalVictories(User user);

    public Long getTotalLooses(User user);

}