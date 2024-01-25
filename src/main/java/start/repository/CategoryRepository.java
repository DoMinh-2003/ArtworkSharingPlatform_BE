package start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import start.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
