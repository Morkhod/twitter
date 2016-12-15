package ru.urfu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.urfu.entitles.Message;
import ru.urfu.entitles.User;
import ru.urfu.model.MessageDao;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageDao storage;

    @RequestMapping(value = "/allMessages", method = RequestMethod.GET)
    public List<String> listAllMessages() {
        return storage.getAllMessages();
    }

    @RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
    public Message getMessage(@PathVariable("id") int id) {
        return storage.find(id);
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public void create(HttpServletResponse response, @RequestParam String data) throws IOException {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        storage.addMessage(data, currentUser.getLogin(), currentUser.getId());
        response.sendRedirect("");
    }

    @RequestMapping(value = "/message/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") int id) {
        Message messageToDelete = storage.find(id);
        if (messageToDelete == null)
            return ResponseEntity.notFound().build();
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int currentUserId = currentUser.getId();
        int msgAuthorId = messageToDelete.getAuthorId();
        if (msgAuthorId == currentUserId) {
            storage.deleteMessage(messageToDelete);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
