package dasturlash.uz.entity;

import dasturlash.uz.enam.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private String password;
    private UserRole role;
    private LocalDateTime created_date ;


    public User(int id,String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}