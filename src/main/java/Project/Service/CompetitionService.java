package Project.Service;

import Project.Model.Competition;

public interface CompetitionService {

    public long add(Competition competition);
    public Competition getById(long id, boolean lazy);

}