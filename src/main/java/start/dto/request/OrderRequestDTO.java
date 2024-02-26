package start.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class OrderRequestDTO {
    UUID creatorID;
    String title;
    String dateStart;
    String dateEnd;
    String description;
    float price;
}