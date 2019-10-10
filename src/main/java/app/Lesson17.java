package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lesson17 {
    public static void main(String[] args) {
//      1) Реализовать удобный поиск
//      2) Использовать JdbcTemplate named prepared statement
        SpringApplication.run(Lesson17.class, args);
    }
}
