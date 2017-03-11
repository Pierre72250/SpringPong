package Project.Service;

import Project.Model.Elo;
import Project.Model.Participation;

public interface EloService {

    public long add(Elo elo);
    public Elo getLastElo(Participation participation);

}