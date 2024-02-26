package start.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import start.enums.StatusEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="audience_id")
    User audience;


    @ManyToOne
    @JoinColumn(name="creator_id")
    User creator;

    private String title;
    private String description;
    private float price;
    private String dateStart;
    private String dateEnd;

    @OneToMany(mappedBy = "orderRequest",cascade = CascadeType.ALL)
    Set<DemoRequest> demoRequests;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;



}
