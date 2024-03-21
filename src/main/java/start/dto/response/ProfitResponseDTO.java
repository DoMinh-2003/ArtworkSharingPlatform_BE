package start.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import start.dto.ListSystemProfitMapByDTO;
import start.entity.SystemProfit;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfitResponseDTO {
    int month;
    float revenuePortal;
    List<ListSystemProfitMapByDTO> systemProfits;

}
