package Project.Service.impl;

import Project.DAO.EloDAO;
import Project.Model.Elo;
import Project.Model.Participation;
import Project.Service.EloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EloServiceImpl implements EloService {

        @Autowired
        private EloDAO eloDAO;

        @Override
        public long add(Elo elo) {
                return  eloDAO.add(elo);
        }

        @Override
        public Elo getLastElo(Participation participation) {
                return eloDAO.getLastElo(participation);
        }
}
