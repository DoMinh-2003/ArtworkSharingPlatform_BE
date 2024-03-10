package start.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import start.enums.StatusEnum;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;    
    private String title;
    private String image;
    private String description;
    private String createDate;
    private float price;
    private String reasonReject;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    // user

    @ManyToOne
    @JoinColumn(name="user_id")
    // khoa ngoai
    User user;

    // category
    @ManyToMany
    @JoinTable(
            name = "artwork_category",
            joinColumns = @JoinColumn(name = "artwork_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonManagedReference
    Set<Category> categories;

    @JsonIgnore
    @OneToMany(mappedBy = "artwork", cascade = CascadeType.ALL)
    private Set<Interaction> interactions;


}
