package start.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import start.entity.User;
import start.enums.RoleEnum;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findUserByUsername(String username);
    User findUserById(UUID id);
    User findByEmail(String email);

//    @Query(value="SELECT u.id, u.name, u.username, u.email, u.avt, u.role FROM user u LEFT JOIN (SELECT user_id, COUNT(id) AS artwork_count FROM artwork GROUP BY user_id) AS a ON u.id = a.user_id ORDER BY artwork_count DESC",nativeQuery = true)
//    List<User> findTopCreatorsByArtworkCount();
    List<User>  findUserByRole(RoleEnum roleEnum);
    @Query("SELECT u, COUNT(a) AS artwork_count FROM User u LEFT JOIN u.artworks a GROUP BY u.id ORDER BY artwork_count DESC")
    List<User> findTopCreatorsByArtworkCount(Pageable pageable);
    List<User> findUsersByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrUsernameContainingIgnoreCase(String name, String email, String username);

    User findByWallet_WalletID(int walletID);

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role1 OR u.role = :role2")
    int countByRole(RoleEnum role1, RoleEnum role2);
}
