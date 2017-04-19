package Project.DAO.impl;

import Project.DAO.ParticipationDAO;
import Project.DAO.ResultatDAO;
import Project.Model.Participation;
import Project.Model.Resultat;
import Project.Model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Calendar;

@Repository
public class ResultatDAOImpl implements ResultatDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(Resultat resultat) {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        resultat.setDate(currentTimestamp);
        Serializable id = sessionFactory.getCurrentSession().save(resultat);
        return (Long) id;
    }

    @Override
    public Long getTotalMatchs(User user) {
        try{
            javax.persistence.Query query = sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM Resultat where idUser_1 = :user OR idUser_2 = :user");
            query.setParameter("user", user);
            return (Long) query.getSingleResult();
        }catch (Exception e){
            return Long.valueOf(0);
        }
    }

    @Override
    public Long getTotalVictories(User user) {
        try{
            javax.persistence.Query query = sessionFactory.getCurrentSession().createQuery("SELECT COUNT(*) FROM Resultat WHERE ( idUser_1 = :user AND scoreUser_1 > scoreUser_2 ) OR ( idUser_2 = :user AND scoreUser_2 > scoreUser_1 )");
            query.setParameter("user", user);
            return (Long) query.getSingleResult();
        }catch (Exception e){
            return Long.valueOf(0);
        }
    }

    @Override
    public Long getTotalLooses(User user) {
        try{
            javax.persistence.Query query = sessionFactory.getCurrentSession().createQuery("SELECT COUNT(*) FROM Resultat WHERE ( idUser_1 = :user AND scoreUser_1 < scoreUser_2 ) OR ( idUser_2 = :user AND scoreUser_2 < scoreUser_1 )");
            query.setParameter("user", user);
            return (Long) query.getSingleResult();
        }catch (Exception e){
            return Long.valueOf(0);
        }
    }

}