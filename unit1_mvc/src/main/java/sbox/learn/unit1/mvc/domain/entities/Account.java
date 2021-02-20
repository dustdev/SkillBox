package sbox.learn.unit1.mvc.domain.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

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
