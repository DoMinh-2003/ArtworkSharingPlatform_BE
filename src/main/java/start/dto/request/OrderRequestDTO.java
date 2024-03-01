package start.dto.request;

import lombok.Getter;
import lombok.Setter;
import start.entity.DemoRequest;

import java.util.Set;
import java.util.UUID;


@Getter
@Setter
public class OrderRequestDTO {
    long id;
    UUID userID;
    private String title;
    private String dateStart;
    private String dateEnd;
    private String description;
    private String status;
    private String productImage;
    private String productMessage;
    private float price;
    private String reasonRejectCreator;
    private String reasonRejectAudience;
   Set<DemoRequest> demoRequests;

}