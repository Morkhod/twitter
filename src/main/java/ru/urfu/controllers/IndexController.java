package ru.urfu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.urfu.entitles.Message;
import ru.urfu.entitles.User;
import ru.urfu.model.MessageDao;
import ru.urfu.model.UserDao;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private MessageDao storage;

    @Autowired
    private UserDao userDao;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/addMessage")
    public String addMessage() {
        return "addMessage";
    }

    @RequestMapping("/showMessage")
    public String showMessage() {
        return "showMessage";
    }

    @RequestMapping("/deleteMessage")
    public String deleteMessage() {
        return "deleteMessage";
    }

    @PostMapping(path = "addMessageData")
    public String addMessageData(@RequestParam String data, Model model) {
        data = data.trim().replaceAll(" +", " ");
        if (data.isEmpty()) {
            model.addAttribute("errorMessage", "Введите текст сообщения");
            return "addMessage";
        }
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUserLogin = currentUser.getLogin();
        int currentUserId = currentUser.getId();
        storage.addMessage(data, currentUserLogin, currentUserId);
        return "redirect:/";
    }

    @RequestMapping({"/messages"})
    public String messages(Model model) {
        model.addAttribute("allItems", storage.getAllMessages());
        List<Message> allMsg=storage.getAll();
        model.addAttribute("allMsg",allMsg);
        model.addAttribute("msgCount",allMsg.size());
        return "messages";
    }

    @GetMapping(path = "showMessageData")
    public String showMessageData(@RequestParam String strId, Model model) {
        int id = Integer.parseInt(strId);
        Message messageToShow = storage.find(id);
        if (messageToShow == null) {
            model.addAttribute("errorMessage", "Сообщения с таким номером не найдено");
            return "showMessage";
        }
        model.addAttribute("content", messageToShow.getScreenedContent());
        model.addAttribute("author", messageToShow.getAuthor());
        model.addAttribute("id", messageToShow.getId());
        return "showMessageData";
    }

    @GetMapping(path = "deleteMessageById")
    public String deleteMessageById(Model model, @RequestParam String strId) {
        int id = Integer.parseInt(strId);
        Message messageToDelete = storage.find(id);
        if (messageToDelete == null) {
            model.addAttribute("errorMessage", "Сообщения с таким номером не найдено");
            return "deleteMessage";
        }
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int currentUserId = currentUser.getId();
        int msgAuthorId = messageToDelete.getAuthorId();
        if (currentUserId != msgAuthorId) {
            model.addAttribute("errorMessage", "Вы можете удалять только ваши сообщения");
            return "deleteMessage";
        }
        storage.deleteMessage(messageToDelete);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/reg")
    public String reg() {
        return "reg";
    }

    @PostMapping(path = "/regin")
    public String regin(Model model, @RequestParam String login, String password) {
        if (userDao.getUser(login) != null) {
            model.addAttribute("errorMessage", "Пользователь с указанным именем уже существует");
            return "reg";
        }
        User user = new User(login,password);
        userDao.save(user);
        return "redirect:/";
    }
}
