package start.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import start.entity.Category;
import start.entity.User;
import start.enums.StatusEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArtworkResponseDTO {
    Long id;
    private String title;
    private String image;
    private String description;
    private String createDate;
    private float price;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    private User user;
    private Set<Category> categories;
}