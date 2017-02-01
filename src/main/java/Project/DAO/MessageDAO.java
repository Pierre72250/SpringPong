package Project.DAO;

import Project.Model.Message;
import Project.Model.User;

public interface MessageDAO {

    public Long addMessage(Message message);

    public Message getById(long id, boolean lazy);

    public void deleteAll(User user);
}
