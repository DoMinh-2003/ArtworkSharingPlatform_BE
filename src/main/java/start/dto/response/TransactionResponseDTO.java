package start.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import start.entity.*;
import start.enums.TransactionEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {

    private UUID transactionID;

    @Enumerated(EnumType.STRING)
    TransactionEnum transactionType;

    private float amount;

    private String description;

    private String transactionDate;

    private Artwork artwork;

    private OrderRequest order;

    Wallet from;

    Wallet to;

    User userFrom;

    User userTo;


}
