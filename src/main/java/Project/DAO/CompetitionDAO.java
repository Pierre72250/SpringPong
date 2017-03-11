package Project.DAO;

import Project.Model.Competition;

public interface CompetitionDAO {

    public Long add(Competition competition);

    public Competition getById(long id, boolean lazy);
}
