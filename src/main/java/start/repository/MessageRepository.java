package start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import start.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
