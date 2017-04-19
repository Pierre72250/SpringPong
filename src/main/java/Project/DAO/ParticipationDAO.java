package Project.DAO;

import Project.Model.Competition;
import Project.Model.Participation;
import Project.Model.User;

public interface ParticipationDAO {

    public Long add(Participation participation);

    public Participation get(Competition competition, User user, Boolean lazy);

}
