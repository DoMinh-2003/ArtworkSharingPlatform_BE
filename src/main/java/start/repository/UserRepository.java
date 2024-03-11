package start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import start.entity.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findUserByUsername(String username);
    User findUserById(UUID id);
    User findByEmail(String email);

    @Query(value="SELECT u.id, u.name, u.username, u.email, u.avt, u.role FROM user u LEFT JOIN (SELECT user_id, COUNT(id) AS artwork_count FROM artwork GROUP BY user_id) AS a ON u.id = a.user_id ORDER BY artwork_count DESC LIMIT 10",nativeQuery = true)
    List<User> findTopCreatorsByArtworkCount();

}
