package start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import start.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
