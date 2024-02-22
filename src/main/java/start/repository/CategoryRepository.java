package start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import start.entity.Category;

import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findAllByName(String name);
}