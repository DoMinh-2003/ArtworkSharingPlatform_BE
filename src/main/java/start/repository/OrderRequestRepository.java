package start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import start.entity.OrderRequest;
import start.enums.StatusEnum;

import java.util.List;
import java.util.UUID;

public interface OrderRequestRepository extends JpaRepository<OrderRequest,Long> {
    List<OrderRequest> findByAudienceIdAndStatus(UUID userId, StatusEnum status);

    List<OrderRequest> findByCreatorIdAndStatus(UUID userId, StatusEnum status);


}
