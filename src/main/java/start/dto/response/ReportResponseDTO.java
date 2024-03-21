package start.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import start.entity.Artwork;
import start.entity.OrderRequest;
import start.entity.User;
import start.enums.ReportEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDTO {
    Long id;

    User from;

    User to;

    private String tittle;

    private String discription;

    private String date;

    private String image;

    private Artwork artwork;

    private OrderRequest order;

    @Enumerated(EnumType.STRING)
    ReportEnum statusReport;
}
