package start.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import start.enums.TransactionEnum;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID transactionID;


    @Enumerated(EnumType.STRING)
    TransactionEnum transactionType;

    private float amount;

    private String description;

    private String transactionDate;

    private Long artworkID;

    private Long orderID;



    @ManyToOne
    @JoinColumn(name = "from_id")
    Wallet from;


    @ManyToOne
    @JoinColumn(name = "to_id")
    Wallet to;

    @JsonIgnore
    @OneToOne(mappedBy = "transaction",cascade = CascadeType.ALL)
    private SystemProfit systemProfit;


}