package start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import start.entity.Message;


@Repository

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
