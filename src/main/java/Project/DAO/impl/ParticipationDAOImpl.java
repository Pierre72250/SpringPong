package Project.DAO.impl;

import Project.DAO.CompetitionDAO;
import Project.DAO.ParticipationDAO;
import Project.Model.Competition;
import Project.Model.Participation;
import Project.Model.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Repository
@Transactional
public class ParticipationDAOImpl implements ParticipationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(Participation participation) {
        Serializable id = sessionFactory.getCurrentSession().save(participation);
        return (Long) id;
    }

    @Override
    public Participation get(Competition competition, User user, Boolean lazy) {
        try{
            javax.persistence.Query query = sessionFactory.getCurrentSession().createQuery("from Participation where user = :user and competition = :competition");
            query.setParameter("competition", competition);
            query.setParameter("user", user);
            if (lazy)
                Hibernate.initialize(((Participation)query.getSingleResult()).getElos());
            return (Participation)query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

}