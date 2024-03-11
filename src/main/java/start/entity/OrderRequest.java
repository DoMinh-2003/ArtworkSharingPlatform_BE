package start.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import start.enums.StatusEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;

    @ManyToOne
    @JoinColumn(name = "audience_id")
    User audience;


    @ManyToOne
    @JoinColumn(name = "creator_id")
    User creator;

    private String title;
    private String description;
    private float price;
    private String dateStart;
    private String dateEnd;
    private String reasonRejectCreator;
    private String reasonRejectAudience;
    private String productImage;
    private String productMessage;

    @JsonIgnore
    @OneToMany(mappedBy = "orderRequest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<DemoRequest> demoRequests;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Override
    public String toString() {
        return "OrderRequest{" +
                "id=" + id +
                ", audience=" + audience +
                ", creator=" + creator +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", dateStart='" + dateStart + '\'' +
                ", dateEnd='" + dateEnd + '\'' +
                ", reasonRejectCreator='" + reasonRejectCreator + '\'' +
                ", reasonRejectAudience='" + reasonRejectAudience + '\'' +
                ", demoRequests=" + demoRequests +
                ", status=" + status +
                '}';
    }
}
