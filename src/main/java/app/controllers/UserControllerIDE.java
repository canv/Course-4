package app.controllers;

import app.models.*;
import app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class UserControllerIDE implements UserController {
    private UserService service;

    @Autowired
    public UserControllerIDE(UserService service) {
        this.service = service;
    }

    @GetMapping("/user")
    @Override
    public String getUserByID(@RequestParam("id") String id, Model model) {
        final User user = service.getByID(id);
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/user")
    @Override
    public String safeUser(@RequestParam("userName") String userName,
                           @RequestParam("password") String password,
                           Model model) {
        User saved = service.save(new User(userName, password));
        model.addAttribute("user", saved);
        return "user";
    }

    @GetMapping("/users")
    @Override
    public String getAllUsers(Model model) {
        Set<User> allUsers = service.getAll();
        model.addAttribute("users",allUsers);
        return "users/allUsers";
    }
}
