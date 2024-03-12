package start.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import start.entity.Artwork;
import start.entity.Transaction;
import start.entity.User;
import start.enums.StatusEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyArtworkResponseDTO {

    Artwork artwork;
    Transaction transaction;



}
