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

    @Autowired
    EmailService emailService;


    public OrderRequest sendOrderRequest(OrderRequestDTO orderRequestDTO) {
        OrderRequest orderRequest = new OrderRequest();
        User creator = userRepository.findUserById(orderRequestDTO.getUserID());
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

    public void threadSendMail(User user,String subject, String description){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                emailService.sendMail(user,subject,description);
            }

        };
        new Thread(r).start();
    }

    public OrderRequest updateOrderRequestCreator(OrderRequestDTO orderRequestDTO) {
        OrderRequest orderRequest = orderRequestRepository.findById(orderRequestDTO.getId());
        if(orderRequestDTO.getStatus().toLowerCase().trim().equals("active")){
            orderRequest.setDateEnd(orderRequestDTO.getDateEnd());
            orderRequest.setPrice(orderRequestDTO.getPrice());
            orderRequest.setStatus(StatusEnum.ACTIVE);
            threadSendMail(orderRequest.getAudience(),"Order Artwork " + orderRequest.getTitle()+ "Success","Thank you for trusting us to use cremo");
        }else{
            orderRequest.setStatus(StatusEnum.REJECT);
            threadSendMail(orderRequest.getAudience(),"Order Artwork " + orderRequest.getTitle()+ "Fail","Creator Cancel With Reason: " +orderRequest.getReasonRejectCreator());
        }
        return orderRequestRepository.save(orderRequest);
    }

    public OrderRequest updateOrderRequestAudience(OrderRequestDTO orderRequestDTO) {
        OrderRequest orderRequest = orderRequestRepository.findById(orderRequestDTO.getId());
        if(orderRequestDTO.getStatus().toLowerCase().trim().equals("processing")){

            orderRequest.setStatus(StatusEnum.PROCESSING);
            threadSendMail(orderRequest.getCreator(),"Order Artwork " + orderRequest.getTitle()+ "Success","Thank you for trusting us to use cremo");
        }else{
            orderRequest.setStatus(StatusEnum.REJECT);
            threadSendMail(orderRequest.getCreator(),"Order Artwork " + orderRequest.getTitle()+ "Fail","Audience Cancel With Reason: " +orderRequest.getReasonRejectAudience());
        }
        return orderRequestRepository.save(orderRequest);
    }

    public List<OrderRequest> getOrderRequestCreatorStatus(StatusEnum status) {
        User user = accountUtils.getCurrentUser();
            List<OrderRequest> listOrderRequest = orderRequestRepository.findByCreatorIdAndStatus(user.getId(),status);
        return listOrderRequest;
    }

    public List<OrderRequest> getOrderRequestAudienceStatus(StatusEnum status) {
        User user = accountUtils.getCurrentUser();
        List<OrderRequest> listOrderRequest = orderRequestRepository.findByAudienceIdAndStatus(user.getId(),status);
        return listOrderRequest;
    }

    public OrderResponseDTO getOrderRequestDetail(long id) {
        OrderRequest orderRequest = orderRequestRepository.findById(id);
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setId(orderRequest.getId());
        orderResponseDTO.setAudience(orderRequest.getAudience());
        orderResponseDTO.setCreator(orderRequest.getCreator());
        orderResponseDTO.setTitle(orderRequest.getTitle());
        orderResponseDTO.setDescription(orderRequest.getDescription());
        orderResponseDTO.setPrice(orderRequest.getPrice());
        orderResponseDTO.setDateStart(orderRequest.getDateStart());
        orderResponseDTO.setDateEnd(orderRequest.getDateEnd());
        orderResponseDTO.setReasonRejectAudience(orderRequest.getReasonRejectAudience());
        orderResponseDTO.setReasonRejectCreator(orderRequest.getReasonRejectCreator());
        orderResponseDTO.setDemoRequests(orderRequest.getDemoRequests());
        orderResponseDTO.setStatus(orderRequest.getStatus());
        return orderResponseDTO;
    }
}
