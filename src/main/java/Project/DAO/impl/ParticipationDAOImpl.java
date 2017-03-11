package Project.DAO.impl;

import Project.DAO.CompetitionDAO;
import Project.DAO.ParticipationDAO;
import Project.Model.Competition;
import Project.Model.Participation;
import Project.Model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class ParticipationDAOImpl implements ParticipationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(Participation participation) {
        Serializable id = sessionFactory.getCurrentSession().save(participation);
        return (Long) id;
    }

}