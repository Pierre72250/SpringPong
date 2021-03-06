package Project.Service.impl;

import Project.DAO.CompetitionDAO;
import Project.DAO.ParticipationDAO;
import Project.Model.Competition;
import Project.Model.Participation;
import Project.Model.User;
import Project.Service.CompetitionService;
import Project.Service.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParticipationServiceImpl implements ParticipationService {

        @Autowired
        private ParticipationDAO participationDAO;

        @Override
        public long add(Participation participation) {
                return  participationDAO.add(participation);
        }

        @Override
        public Participation get(Competition competition, User user, Boolean lazy) {
                return participationDAO.get(competition, user, lazy);
        }

}
