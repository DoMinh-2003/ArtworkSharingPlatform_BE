package start.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import start.enums.CategoryEnum;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    CategoryEnum categoryEnum;

    @ManyToMany(mappedBy = "categories",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    Set<Artwork> artworks;
}
