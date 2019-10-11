package app.controllers;

import app.exceptions.UserNotFoundException;
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
    public String getUserByID(@RequestParam("id") String id,
                              Model model) {
        final User user = service.getByID(id);
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/user")
    @Override
    public String safeUser(@RequestParam("userName") String userName,
                           @RequestParam("password") String password,
                           Model model) {
        final User saved = service.save(new User(userName, password));
        model.addAttribute("user", saved);
        return "user";
    }

    @PostMapping("/del")
    @Override
    public String deleteUser(@RequestParam("id") String id,
                             @RequestParam("userName") String userName,
                             @RequestParam("password") String password,
                             Model model) {
        User isDelete = service.getByID(id);
        if (!userName.equals(isDelete.getUsername())
                && !password.equals(isDelete.getMd5Password()))
            throw new UserNotFoundException("User is not found!");
        User deletedUser = service.deleteUser(isDelete);
        model.addAttribute("user", deletedUser);
        return "/del";
    }

    @GetMapping("/users")
    @Override
    public String getAllUsers(Model model) {
        Set<User> allUsers = service.getAll();
        model.addAttribute("users", allUsers);
        return "users/allUsers";
    }
}
