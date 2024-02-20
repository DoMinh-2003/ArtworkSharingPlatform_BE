package start.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;


@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;

    @ManyToMany(mappedBy = "categories",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    Set<Artwork> artworks;
}
