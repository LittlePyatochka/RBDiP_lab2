package lab2.kamysh.dto;

import lombok.*;
import lab2.kamysh.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String login;
    private String password;

    public UserDto(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
    }
}