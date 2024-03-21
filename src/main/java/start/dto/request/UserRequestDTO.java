package start.dto.request;


import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequestDTO {
    private String name;
    private String email;
    private String avt;
    private String oldPassword;
    private String newPassword;


}
