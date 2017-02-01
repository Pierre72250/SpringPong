package Project.Service.impl;

import Project.DAO.MessageDAO;
import Project.Model.Message;
import Project.Model.User;
import Project.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO;

    @Override
    public void add(Message message) {
        messageDAO.addMessage(message);
    }

    @Override
    public Message getById(long id, boolean lazy) {
           return messageDAO.getById(id, lazy);
    }

    @Override
    public void deleteAll(User user) {
            messageDAO.deleteAll(user);
    }
}
