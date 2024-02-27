package start.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class OrderRequestDTO {
    long id;
    UUID userID;
    String title;
    String dateStart;
    String dateEnd;
    String description;
    float price;
}