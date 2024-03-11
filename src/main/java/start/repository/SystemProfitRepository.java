package start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import start.entity.SystemProfit;

@Repository
public interface SystemProfitRepository extends JpaRepository<SystemProfit,Long> {
    SystemProfit findById(long id);
}
