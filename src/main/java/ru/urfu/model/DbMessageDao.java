package ru.urfu.model;

import org.springframework.stereotype.Repository;
import ru.urfu.entitles.Message;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Named
public class DbMessageDao implements MessageDao {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void addMessage(String message, String author, int authorId) {
        Message msg = new Message(message, author, authorId);
        em.persist(msg);
    }

    @Override
    public Message find(int id) {
        return em.find(Message.class, id);
    }

    @Transactional
    @Override
    public void deleteMessage(Message msg) {
        em.remove(msg);
    }

    @Override
    public List<String> getAllMessages() {
        List<Message> messageList = em.createQuery("from " + Message.class.getName(), Message.class)
                .getResultList();
        return messageList.stream()
                .map(msg -> msg.getScreenedContent() + " from: " + msg.getAuthor() + " id: " + msg.getId())
                .collect(Collectors.toList());
    }

    @Override
    public List<Message> getAll() {
        return em.createQuery("from " + Message.class.getName(), Message.class)
                .getResultList();
    }
}

