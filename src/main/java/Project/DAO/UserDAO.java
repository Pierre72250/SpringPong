package Project.DAO;

import Project.Model.User;

public interface UserDAO {

    public Long add(User user);

    public void update(User user);

    public User getById(long id, boolean lazy);

    public User getByMail(String mail);

    public void delete(User user);
}
