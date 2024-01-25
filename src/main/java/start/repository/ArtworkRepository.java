package start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import start.entity.Artwork;

public interface ArtworkRepository extends JpaRepository<Artwork,Long> {

}
