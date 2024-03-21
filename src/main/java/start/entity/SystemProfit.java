package start.entity;

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

    @OneToOne(mappedBy = "systemProfit",cascade = CascadeType.ALL)
    Transaction transaction;
}
