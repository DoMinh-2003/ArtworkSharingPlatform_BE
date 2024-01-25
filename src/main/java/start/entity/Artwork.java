package start.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import start.enums.StatusEnum;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    private String name;
    private String image;

    private String description;
    @ManyToOne

    @JoinColumn(name="user_id")
    // khoa ngoai
    User user;
    private Date createDate;
    private float price;
    private StatusEnum status;

    @ManyToMany
    @JoinTable(
            name = "artwork_category",
            joinColumns = @JoinColumn(name = "artwork_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
         Set<Category> categories;

}
