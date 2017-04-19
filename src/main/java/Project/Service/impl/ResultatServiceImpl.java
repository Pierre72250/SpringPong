package Project.Service.impl;

import Project.DAO.ParticipationDAO;
import Project.DAO.ResultatDAO;
import Project.Model.Participation;
import Project.Model.Resultat;
import Project.Model.User;
import Project.Service.ParticipationService;
import Project.Service.ResulatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResultatServiceImpl implements ResulatService {

        @Autowired
        private ResultatDAO resultatDAO;

        @Override
        public long add(Resultat resultat) {
                return resultatDAO.add(resultat);
        }

        @Override
        public Long getTotalMatchs(User user) {
                return resultatDAO.getTotalMatchs(user);
        }

        @Override
        public Long getTotalVictories(User user) {
                return resultatDAO.getTotalVictories(user);
        }

        @Override
        public Long getTotalLooses(User user) {
                return resultatDAO.getTotalLooses(user);
        }
}
