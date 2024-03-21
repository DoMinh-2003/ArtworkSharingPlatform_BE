package start.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import start.entity.Transaction;
import start.entity.User;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListSystemProfitMapByDTO {
    Long id;
    float balance;
    String description;
    String date;
    Transaction transaction;
    User userForm;
    User userTo;
}
