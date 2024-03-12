package start.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int walletID;
    private float balance;
    private float cocMoney;

@JsonIgnore
    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", unique = true)
    private User user;


    @JsonIgnore
    @OneToMany(mappedBy = "from", cascade = CascadeType.ALL)
    private Set<Transaction> transactionsFrom;

    @JsonIgnore
    @OneToMany(mappedBy = "to", cascade = CascadeType.ALL)
    private Set<Transaction> transactionsTo;

}
