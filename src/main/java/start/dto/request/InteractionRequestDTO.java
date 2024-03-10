package start.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import start.enums.TypeEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InteractionRequestDTO {
    private String content;
    private String createDate;
    private String type;
    private long artworkId;
}
