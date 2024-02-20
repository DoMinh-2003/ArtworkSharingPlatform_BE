package start.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import start.enums.RoleEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDTO {
    private String userName;
    private String password;
    private String name;
    private String email;
    private String role;
    private String phone;
}
