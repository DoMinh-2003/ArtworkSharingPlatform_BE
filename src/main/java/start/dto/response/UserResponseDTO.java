package start.dto.response;



import lombok.*;
import start.entity.Artwork;
import start.enums.RoleEnum;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDTO {

    private UUID id;
    private String name;
    private String username;
    private String email;
    private Integer postQuantity;
    private String avt;
    private boolean active;
    private String token;
    @Enumerated(EnumType.STRING)
    RoleEnum role;
    Set<Artwork> artworks;

}
