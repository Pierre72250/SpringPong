package Project.DAO.impl;

import Project.DAO.CompetitionDAO;
import Project.DAO.UserDAO;
import Project.Model.Competition;
import Project.Model.User;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class CompetitionDAOImpl implements CompetitionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(Competition competition) {
        Serializable id = sessionFactory.getCurrentSession().save(competition);
        return (Long) id;
    }

    @Override
    public Competition getById(long id, boolean lazy) {
        Competition competition =  sessionFactory.getCurrentSession().get(Competition.class, id);
        if (lazy)
            Hibernate.initialize(competition.getParticipations());
            Hibernate.initialize(competition.getResultats());
        return competition;
    }
}