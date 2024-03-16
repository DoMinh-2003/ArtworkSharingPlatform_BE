package start.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemoRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String image;
    private String description;
    private String comment;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="orderRequest_id")
    OrderRequest orderRequest;
}
