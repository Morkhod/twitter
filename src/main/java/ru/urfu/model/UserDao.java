package ru.urfu.model;

import ru.urfu.entitles.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void save(User user);
    User getUser(String login);
    Optional<User> getUser(int id);
    List<User> getAllUsers();
}
