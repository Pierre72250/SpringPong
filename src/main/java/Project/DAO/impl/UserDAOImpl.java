package Project.DAO.impl;

import Project.DAO.UserDAO;
import Project.Model.User;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class UserDAOImpl implements UserDAO {

    // List cats = session.createCriteria(Cat.class) .add( Restrictions.like("name", "Fritz%") ) .setFetchMode("mate", FetchMode.EAGER) .setFetchMode("kittens", FetchMode.EAGER) .list();

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(User user) {
        Serializable id = sessionFactory.getCurrentSession().save(user);
        return (Long) id;
    }

    @Override
    public User getById(long id, boolean lazy) {
        User user =  sessionFactory.getCurrentSession().get(User.class, id);
        if (lazy)
            Hibernate.initialize(user.getMessages());
        return user;
    }

    @Override
    public User getByMail(String mail) {
        try{
            javax.persistence.Query query = sessionFactory.getCurrentSession().createQuery("from User where mail = :mail");
            query.setParameter("mail", mail);
            return (User)query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().remove(user);
    }
}