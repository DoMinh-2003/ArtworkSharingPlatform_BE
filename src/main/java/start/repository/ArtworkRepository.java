package start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import start.entity.Artwork;
import start.entity.Category;
import start.enums.StatusEnum;

import java.util.List;
import java.util.UUID;

@Repository
public interface ArtworkRepository extends JpaRepository<Artwork,Long> {
    List<Artwork> findByStatus(StatusEnum status);

    List<Artwork> findByUserIdAndStatus(UUID id,StatusEnum status);
    Artwork findById(long id);

    @Query("SELECT a FROM Artwork a WHERE a.status = :status AND (LOWER(a.title) LIKE %:search% OR LOWER(a.description) LIKE %:search%)")
    List<Artwork> findByStatusAndTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(@Param("status") StatusEnum status, @Param("search") String search);

    List<Artwork> findByCategoriesNameIn(List<String> categoryNames);
}
