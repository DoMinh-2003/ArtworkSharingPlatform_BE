package start.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import start.dto.response.LoginResponse;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtworkRequestDTO {
    private String title;
    private String image;
    private String description;
    private float price;
    private String createDate;
    private Set<String> categoriesName;
//    private LoginResponse user;
}
