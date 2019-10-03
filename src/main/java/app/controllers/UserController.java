package app.controllers;

import app.models.User;
import org.springframework.ui.Model;

public interface UserController {
    String getUserByID(String id, Model model);
    String safeUser(String userName, String password, Model model);
    String getAllUsers(Model model);
    String userDemo(Model model);
}
