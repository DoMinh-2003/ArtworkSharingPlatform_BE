package start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import start.entity.Artwork;
import start.enums.StatusEnum;

import java.util.List;

public interface ArtworkRepository extends JpaRepository<Artwork,Long> {
    List<Artwork> findByStatus(StatusEnum status);
    Artwork findById(long id);

}
