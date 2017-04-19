package Project.DAO.impl;

import Project.DAO.EloDAO;
import Project.Model.Elo;
import Project.Model.Participation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Calendar;

@Repository
@Transactional
public class EloDAOImpl implements EloDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(Elo elo) {
        Serializable id = sessionFactory.getCurrentSession().save(elo);
        return (Long) id;
    }

    @Override
    public Elo getLastElo(Participation participation) {
        try{
            javax.persistence.Query query = sessionFactory.getCurrentSession().createQuery("FROM Elo where idParticipation = :participation and date = ( SELECT MAX(date) FROM Elo where idParticipation = :participation )");
            query.setParameter("participation", participation);
            return (Elo)query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

}