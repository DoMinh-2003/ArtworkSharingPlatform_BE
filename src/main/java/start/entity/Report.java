package start.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import start.enums.ReportEnum;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "from_id")
    User from;

    @ManyToOne
    @JoinColumn(name = "to_id")
    User to;

    private String tittle;

    private String discription;

    private String date;

    private Long artworkID;

    private Long orderID;


    @Enumerated(EnumType.STRING)
    ReportEnum statusReport;

}
