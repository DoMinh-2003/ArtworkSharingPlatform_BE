package start.entity;



import javax.persistence.*;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import start.enums.RoleEnum;
import start.enums.TransactionEnum;

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
    @JoinColumn(name = "form_id")
    Wallet from;


    @ManyToOne
    @JoinColumn(name = "to_id")
    Wallet to;



}