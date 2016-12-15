package ru.urfu.model;

import ru.urfu.entitles.Message;

import java.util.List;

public interface MessageDao {
    Message find(int id);
    void addMessage(String message, String author,int authorId);
    void deleteMessage(Message msg);
    List<String> getAllMessages();
    List<Message> getAll();
}
