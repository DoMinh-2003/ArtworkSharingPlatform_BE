package start.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import start.dto.request.OrderRequestDTO;
import start.dto.response.OrderResponseDTO;
import start.entity.OrderRequest;
import start.enums.StatusEnum;
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
    @PutMapping ("/updateOrderRequest-creator")
    public ResponseEntity updateOrderRequestCreator(@RequestBody OrderRequestDTO orderRequestDTO){
        OrderRequest orderRequest = orderRequestService.updateOrderRequestCreator(orderRequestDTO);
        return  responseHandler.response(200, "Update Order Creator Successfully!", orderRequest);
    }
    @PutMapping ("/updateOrderRequest-audience")
    public ResponseEntity updateOrderRequestAudience(@RequestBody OrderRequestDTO orderRequestDTO){
        OrderRequest orderRequest = orderRequestService.updateOrderRequestAudience(orderRequestDTO);
        return  responseHandler.response(200, "Update Order Audience Successfully!", orderRequest);
    }
    @GetMapping("/getOrderRequestAudience-pending")
    public ResponseEntity getOrderRequestAudience(){
        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestAudienceStatus(StatusEnum.PENDING);
        return  responseHandler.response(200, "Get Order Pending Audience Successfully!", listOrderRequest);
    }
    @GetMapping("/getOrderRequestCreator-pending")
    public ResponseEntity getOrderRequestCreator(){
        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestCreatorStatus(StatusEnum.PENDING);
        return  responseHandler.response(200, "Get Order Pending Creator Successfully!", listOrderRequest);
    }
    @GetMapping("/getOrderRequestCreator-active")
    public ResponseEntity getOrderRequestCreatorActive(){
        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestCreatorStatus(StatusEnum.ACTIVE);
        return  responseHandler.response(200, "Get Order Active Creator Successfully!", listOrderRequest);
    }
    @GetMapping("/getOrderRequestAudience-active")
    public ResponseEntity getOrderRequestAudienceActive(){
        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestAudienceStatus(StatusEnum.ACTIVE);
        return  responseHandler.response(200, "Get Order Active Audience Successfully!", listOrderRequest);
    }

    @GetMapping("/getOrderRequestCreator-processing")
    public ResponseEntity getOrderRequestCreatorProcessing(){
        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestCreatorStatus(StatusEnum.PROCESSING);
        return  responseHandler.response(200, "Get Order Active Creator Successfully!", listOrderRequest);
    }
    @GetMapping("/getOrderRequestAudience-processing")
    public ResponseEntity getOrderRequestAudienceProcessing(){
        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestAudienceStatus(StatusEnum.PROCESSING);
        return  responseHandler.response(200, "Get Order Active Audience Successfully!", listOrderRequest);
    }

    @GetMapping("/getOrderRequestCreator-done")
    public ResponseEntity getOrderRequestDoneCreator(){
        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestCreatorStatus(StatusEnum.DONE);
        return  responseHandler.response(200, "Get Order Done Creator Successfully!", listOrderRequest);
    }
    @GetMapping("/getOrderRequestAudience-done")
    public ResponseEntity getOrderRequestDoneAudience(){
        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestAudienceStatus(StatusEnum.DONE);
        return  responseHandler.response(200, "Get Order Done Audience Successfully!", listOrderRequest);
    }
    @GetMapping("/getAllOrderRequestAudience")
    public ResponseEntity getAllOrderRequestAudience(){
        List<OrderRequest> listOrderRequest = orderRequestService.getAllOrderRequestAudience();
        return  responseHandler.response(200, "Get Order Done Audience Successfully!", listOrderRequest);
    }
    @GetMapping("/getAllOrderRequestCreator")
    public ResponseEntity getAllOrderRequestCreator(){
        List<OrderRequest> listOrderRequest = orderRequestService.getAllOrderRequestCreator();
        return  responseHandler.response(200, "Get Order Done Audience Successfully!", listOrderRequest);
    }

    @GetMapping("/getOrderRequestDetail/{id}")
    public ResponseEntity getOrderRequestDetail(@PathVariable long id){
        OrderResponseDTO responseDTO = orderRequestService.getOrderRequestDetail(id);
        return  responseHandler.response(200, "Get Order Detail Successfully!", responseDTO);
    }
}