package start.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberToTalResponseDTO {
    int totalMember;
    int modQuantity;
    int audienceQuantity;
    int creatorQuantity;
}
