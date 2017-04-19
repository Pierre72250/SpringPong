package Project.Service;

import Project.Model.Competition;
import Project.Model.Participation;
import Project.Model.User;

public interface ParticipationService {

    public long add(Participation participation);

    public Participation get(Competition competition, User user, Boolean lazy);

}