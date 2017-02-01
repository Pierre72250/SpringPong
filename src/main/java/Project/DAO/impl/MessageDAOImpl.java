package Project.DAO.impl;

import Project.DAO.MessageDAO;
import Project.Model.Message;
import Project.Model.User;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class MessageDAOImpl implements MessageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long addMessage(Message message) {
        Serializable id = sessionFactory.getCurrentSession().save(message);
        return (Long) id;
    }

    @Override
    public Message getById(long id, boolean lazy) {
        Message message = sessionFactory.getCurrentSession().get(Message.class, id);
        if (lazy)
            Hibernate.initialize(message.getUser());
        return message;
    }

    @Override
    public void deleteAll(User user) {
        Query q3 = sessionFactory.getCurrentSession().createQuery("delete Message WHERE user = :user");
        q3.setParameter("user", user);
        q3.executeUpdate();
    }
}
