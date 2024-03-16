package start.dto.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApproveRequestDTO {
   private String status;
   private String description;
}
