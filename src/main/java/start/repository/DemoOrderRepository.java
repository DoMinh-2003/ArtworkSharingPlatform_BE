package start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import start.entity.DemoRequest;


@Repository
public interface DemoOrderRepository extends JpaRepository<DemoRequest,Long> {

}
