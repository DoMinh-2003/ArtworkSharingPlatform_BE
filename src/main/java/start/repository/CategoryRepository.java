package start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import start.entity.Category;
import start.enums.CategoryEnum;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findAllByName(String name);

    Category findById(long id);



    List<Category> findByCategoryEnum(CategoryEnum categoryEnum);
}