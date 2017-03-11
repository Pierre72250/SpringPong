package Project.DAO;

import Project.Model.Elo;
import Project.Model.Participation;

public interface EloDAO {

    public Long add(Elo elo);
    public Elo getLastElo(Participation participation);

}
