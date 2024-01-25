package start.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtworkRequestDTO {
    private String name;
    private String image;
    private String description;
    private float price;
    Set<Long> categoriesID;
}
