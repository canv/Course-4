package app;

import app.controllers.UserController;
import app.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MyFrameworkBootStart("lesson15.app")
@SpringBootApplication
public class Lesson17 {
    public static void main(String[] args) {
//      1) Повторить чтоб всё работало
//      2) Реализовать поиск на главной странице
//      3) Использовать JdbTemplate named prepare statement
        SpringApplication.run(Lesson17.class, args);
    }
}