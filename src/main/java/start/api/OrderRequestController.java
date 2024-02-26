package start.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import start.dto.request.OrderRequestDTO;
import start.dto.response.OrderResponseDTO;
import start.entity.Artwork;
import start.entity.OrderRequest;
import start.service.OrderRequestService;
import start.utils.ResponseHandler;

import java.util.List;

@RestController
@SecurityRequirement(name ="api")
public class OrderRequestController {

    @Autowired
    OrderRequestService orderRequestService;

    @Autowired
    ResponseHandler responseHandler;

    @PostMapping("/sendOrderRequest")
    public ResponseEntity sendOrderRequest(@RequestBody OrderRequestDTO orderRequestDTO){
        OrderRequest orderRequest = orderRequestService.sendOrderRequest(orderRequestDTO);
        return  responseHandler.response(200, "Send Order Successfully!", orderRequest);
    }
    @GetMapping("/getOrderRequest-audience")
    public ResponseEntity getOrderRequestAudience(){
        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestAudience();
        return  responseHandler.response(200, "get Order Audience Successfully!", listOrderRequest);
    }
    @GetMapping("/getOrderRequest-creator")
    public ResponseEntity getOrderRequestCreator(){
        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestCreator();
        return  responseHandler.response(200, "get Order Creator Successfully!", listOrderRequest);
    }
}