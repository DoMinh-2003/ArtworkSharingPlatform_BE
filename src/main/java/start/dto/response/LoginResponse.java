package start.dto.response;

import lombok.Data;
import start.entity.User;

@Data
public class LoginResponse extends User {
    String token;
}
