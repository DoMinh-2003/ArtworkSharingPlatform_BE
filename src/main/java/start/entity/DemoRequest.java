package start.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String image;
    private String description;
    private String comment;

    @ManyToOne
    @JoinColumn(name="order_request")
    OrderRequest orderRequest;
}
