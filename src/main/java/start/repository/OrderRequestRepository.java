package start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import start.entity.OrderRequest;
import start.enums.StatusEnum;

import java.util.List;
import java.util.UUID;


@Repository 
public interface OrderRequestRepository extends JpaRepository<OrderRequest,Long> {
    List<OrderRequest> findByAudienceIdAndStatus(UUID userId, StatusEnum status);

    List<OrderRequest> findByCreatorIdAndStatus(UUID userId, StatusEnum status);

    List<OrderRequest> findByCreatorId(UUID userId);
    List<OrderRequest> findByAudienceId(UUID userId);


//    List<OrderRequest> findByCreatorIdAndStatusIn(UUID userId,List<StatusEnum> statuses);
//
//    List<OrderRequest> findByAudienceIdAndStatusIn(UUID userId,List<StatusEnum> statuses);

    OrderRequest findOrderRequestById(long id);

}
