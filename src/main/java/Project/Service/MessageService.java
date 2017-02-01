package Project.Service;

import Project.Model.Message;
import Project.Model.User;

public interface MessageService {

    public void add(Message user);

    public Message getById(long id, boolean lazy);

    public void deleteAll(User user);

}