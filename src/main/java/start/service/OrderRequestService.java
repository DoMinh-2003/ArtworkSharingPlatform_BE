package start.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.dto.request.OrderRequestDTO;
import start.dto.response.OrderResponseDTO;
import start.entity.OrderRequest;
import start.entity.User;
import start.enums.RoleEnum;
import start.enums.StatusEnum;
import start.repository.OrderRequestRepository;
import start.repository.UserRepository;
import start.utils.AccountUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderRequestService {

    @Autowired
    OrderRequestRepository orderRequestRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountUtils accountUtils;


    public OrderRequest sendOrderRequest(OrderRequestDTO orderRequestDTO) {
        OrderRequest orderRequest = new OrderRequest();
        User creator = userRepository.findUserById(orderRequestDTO.getCreatorID());
        User audience = accountUtils.getCurrentUser();
            orderRequest.setCreator(creator);
            orderRequest.setAudience(audience);
            orderRequest.setTitle(orderRequestDTO.getTitle());
            orderRequest.setDescription(orderRequestDTO.getDescription());
            orderRequest.setDateStart(orderRequestDTO.getDateStart());
            orderRequest.setDateEnd(orderRequestDTO.getDateEnd());
            orderRequest.setPrice(orderRequestDTO.getPrice());
            orderRequest.setStatus(StatusEnum.PENDING);



        return orderRequestRepository.save(orderRequest);
    }

    public List<OrderRequest> getOrderRequestCreator() {
        User user = accountUtils.getCurrentUser();
            List<OrderRequest> listOrderRequest = orderRequestRepository.findByCreator_IdAndStatus(user.getId(),StatusEnum.PENDING);

        return listOrderRequest;
    }

    public List<OrderRequest> getOrderRequestAudience() {
        User user = accountUtils.getCurrentUser();
        List<OrderRequest> listOrderRequest = orderRequestRepository.findByAudience_IdAndStatus(user.getId(),StatusEnum.PENDING);
        return listOrderRequest;
    }
}
