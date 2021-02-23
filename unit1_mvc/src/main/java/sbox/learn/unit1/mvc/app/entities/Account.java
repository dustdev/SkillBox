package sbox.learn.unit1.mvc.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Пользователь
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @NotBlank(message = "Username should not be empty")
    private String username;
    @NotBlank(message = "Password should not be empty")
    private String password;
    @NotBlank(message = "Fullname should not be empty")
    private String fullname;

    @Override
    public String toString() {
        return "Account {" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
