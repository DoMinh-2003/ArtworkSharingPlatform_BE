package start.entity;

        import com.fasterxml.jackson.annotation.JsonIgnore;
        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;

        import javax.persistence.*;
        import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SystemProfit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    float balance;
    String description;
    String date;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id")
    Transaction transaction;
}
