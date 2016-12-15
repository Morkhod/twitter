package ru.urfu.model;

import org.springframework.stereotype.Repository;
import ru.urfu.entitles.User;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Named
public class DbUserDao implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public User getUser(String login) {
        List<User> resultList = em.createQuery("from " + User.class.getName() + " usr " + "where usr.login = :login", User.class)
                .setParameter("login", login)
                .getResultList();
        if (!resultList.isEmpty())
            return resultList.get(0);
        else
            return null;
    }

    @Override
    public Optional<User> getUser(int id) {
        return Optional.of(em.find(User.class, id));
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("from " + User.class.getName(), User.class)
                .getResultList();
    }
}
