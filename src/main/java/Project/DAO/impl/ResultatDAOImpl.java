package Project.DAO.impl;

import Project.DAO.ParticipationDAO;
import Project.DAO.ResultatDAO;
import Project.Model.Participation;
import Project.Model.Resultat;
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

}