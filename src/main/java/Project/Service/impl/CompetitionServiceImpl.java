package Project.Service.impl;

import Project.DAO.CompetitionDAO;
import Project.DAO.UserDAO;
import Project.Model.Competition;
import Project.Model.User;
import Project.Service.CompetitionService;
import Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompetitionServiceImpl implements CompetitionService {

        @Autowired
        private CompetitionDAO competitionDAO;

        @Override
        public long add(Competition competition) {
                return  competitionDAO.add(competition);
        }

        @Override
        public Competition getById(long id, boolean lazy) {
                return competitionDAO.getById(id, lazy);
        }
}
