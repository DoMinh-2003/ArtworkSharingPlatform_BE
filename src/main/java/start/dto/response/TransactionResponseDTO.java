package start.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import start.entity.Artwork;
import start.entity.OrderRequest;
import start.entity.Transaction;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {
    List<Artwork> artwork;
    List<Transaction> transaction;
    List<OrderRequest> orderRequest;
}
