package start.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import start.entity.DemoRequest;
import start.entity.User;
import start.enums.StatusEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    Long id;
    User audience;
    User creator;
    private String title;
    private String description;
    private float price;
    private String dateStart;
    private String dateEnd;
    private String reasonRejectCreator;
    private String reasonRejectAudience;
    Set<DemoRequest> demoRequests;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}
