package start.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import start.entity.Interaction;
import start.enums.TypeEnum;

import java.util.List;
import java.util.UUID;


@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {

    Interaction findByUser_IdAndArtwork_IdAndType(UUID userId, long artworkId, TypeEnum type);
    List<Interaction> findByUser_IdAndType(UUID userId, TypeEnum type);

}
