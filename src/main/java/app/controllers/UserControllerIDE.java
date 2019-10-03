package app.controllers;

import app.models.*;
import app.services.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class UserControllerIDE implements UserController {
    private UserService service;

    @Autowired
    UserControllerIDE(UserService service) {
        this.service = service;
    }

    @Override
    public String getUserByID(String id, Model model) {
        return service.getByID(id).toString();
    }

    @Override
    public String safeUser(String userName, String password, Model model) {
        User saved = service.save(new User(userName, password));
        return saved.toString();
    }

    @Override
    public String getAllUsers(Model model) {
        return service.getAll().stream()
                .map(User::toString)
                .collect(Collectors.joining("\n"));
    }

    @GetMapping("/user")
    @Override
    public String userDemo(Model model) {
        final User user = new User(UUID.randomUUID(),"admin", "root");
        model.addAttribute("user", user);
        return "user";
    }
}
