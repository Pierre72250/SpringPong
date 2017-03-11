package Project.DAO.impl;

import Project.DAO.EloDAO;
import Project.Model.Elo;
import Project.Model.Participation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Calendar;

@Repository
public class EloDAOImpl implements EloDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(Elo elo) {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        elo.setDate(currentTimestamp);

        Serializable id = sessionFactory.getCurrentSession().save(elo);
        return (Long) id;
    }

    @Override
    public Elo getLastElo(Participation participation) {
        try{
            javax.persistence.Query query = sessionFactory.getCurrentSession().createQuery("FROM Elo where idParticipation = :participation and date = ( SELECT MAX(date) FROM Elo )");
            query.setParameter("participation", participation);
            return (Elo)query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

}