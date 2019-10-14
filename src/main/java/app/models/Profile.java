package app.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Profile {
    private UUID userID;
    private String userName;
    private String password;
    private float balance;

    public Profile(String userName, String password, float balance) {
        this.userID = UUID.randomUUID();
        this.userName = userName;
        this.password = password;
        this.balance = balance;
    }
}
