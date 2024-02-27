package start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import start.entity.Artwork;
import start.enums.StatusEnum;

import java.util.List;
import java.util.UUID;

public interface ArtworkRepository extends JpaRepository<Artwork,Long> {
    List<Artwork> findByStatus(StatusEnum status);

    List<Artwork> findByUserIdAndStatus(UUID id,StatusEnum status);
    Artwork findById(long id);
}
