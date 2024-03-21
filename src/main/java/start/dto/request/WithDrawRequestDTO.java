package start.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WithDrawRequestDTO {
    String accountNumber;
    String accountName;
    String bankName;
    float amount;
}
