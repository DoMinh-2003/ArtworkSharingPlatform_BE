package start.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoOrderRequestDTO {
    private long OrderId;
    private String image;
    private String description;
    private String comment;
}
