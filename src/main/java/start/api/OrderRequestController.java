package start.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import start.dto.request.DemoOrderRequestDTO;
import start.dto.request.OrderRequestDTO;
import start.dto.response.OrderResponseDTO;
import start.entity.DemoRequest;
import start.entity.OrderRequest;
import start.enums.StatusEnum;
import start.service.OrderRequestService;
import start.utils.ResponseHandler;

import java.util.List;

@RestController
@SecurityRequirement(name ="api")
@CrossOrigin("*")

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
    @PostMapping("/sendOrderRequestGlobal")
    public ResponseEntity sendOrderRequestGlobal(@RequestBody OrderRequestDTO orderRequestDTO){
        OrderRequest orderRequest = orderRequestService.sendOrderRequestGlobal(orderRequestDTO);
        return  responseHandler.response(200, "Send Order Global Successfully!", orderRequest);
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

    @PutMapping ("/updateOrderRequestGlobal")
    public ResponseEntity updateOrderRequestGlobal(@RequestBody OrderRequestDTO orderRequestDTO){
        OrderRequest orderRequest = orderRequestService.updateOrderRequestGlobal(orderRequestDTO);
        return  responseHandler.response(200, "Update Order Audience Successfully!", orderRequest);
    }

//    @GetMapping("/getOrderRequestAudience-pending")
//    public ResponseEntity getOrderRequestAudience(){
//        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestAudienceStatus(StatusEnum.PENDING);
//        return  responseHandler.response(200, "Get Order Pending Audience Successfully!", listOrderRequest);
//    }
//    @GetMapping("/getOrderRequestCreator-pending")
//    public ResponseEntity getOrderRequestCreator(){
//        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestCreatorStatus(StatusEnum.PENDING);
//        return  responseHandler.response(200, "Get Order Pending Creator Successfully!", listOrderRequest);
//    }
//    @GetMapping("/getOrderRequestCreator-active")
//    public ResponseEntity getOrderRequestCreatorActive(){
//        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestCreatorStatus(StatusEnum.ACTIVE);
//        return  responseHandler.response(200, "Get Order Active Creator Successfully!", listOrderRequest);
//    }
//    @GetMapping("/getOrderRequestAudience-active")
//    public ResponseEntity getOrderRequestAudienceActive(){
//        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestAudienceStatus(StatusEnum.ACTIVE);
//        return  responseHandler.response(200, "Get Order Active Audience Successfully!", listOrderRequest);
//    }
//
//    @GetMapping("/getOrderRequestCreator-processing")
//    public ResponseEntity getOrderRequestCreatorProcessing(){
//        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestCreatorStatus(StatusEnum.PROCESSING);
//        return  responseHandler.response(200, "Get Order Active Creator Successfully!", listOrderRequest);
//    }
//    @GetMapping("/getOrderRequestAudience-processing")
//    public ResponseEntity getOrderRequestAudienceProcessing(){
//        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestAudienceStatus(StatusEnum.PROCESSING);
//        return  responseHandler.response(200, "Get Order Active Audience Successfully!", listOrderRequest);
//    }
//
//    @GetMapping("/getOrderRequestCreator-done")
//    public ResponseEntity getOrderRequestDoneCreator(){
//        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestCreatorStatus(StatusEnum.DONE);
//        return  responseHandler.response(200, "Get Order Done Creator Successfully!", listOrderRequest);
//    }
//    @GetMapping("/getOrderRequestAudience-done")
//    public ResponseEntity getOrderRequestDoneAudience(){
//        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestAudienceStatus(StatusEnum.DONE);
//        return  responseHandler.response(200, "Get Order Done Audience Successfully!", listOrderRequest);
//    }

    @GetMapping("/getOrderRequestAudience-global")
    public ResponseEntity getOrderRequestGlobalAudience(){
        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestAudienceStatus(StatusEnum.GLOBAL);
        return  responseHandler.response(200, "Get Order Global My Audience Successfully!", listOrderRequest);
    }
    @GetMapping("/getAllOrderRequest-global")
    public ResponseEntity getAllOrderRequestGlobal(){
        List<OrderRequest> listOrderRequest = orderRequestService.getAllOrderRequestGlobal(StatusEnum.GLOBAL);
        return  responseHandler.response(200, "Get All Order Global  Successfully!", listOrderRequest);
    }

//    @GetMapping("/getOrderRequestCreator-reject")
//    public ResponseEntity getOrderRequestDenyCreator(){
//        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestCreatorStatus(StatusEnum.REJECT);
//        return  responseHandler.response(200, "Get Order Reject Creator Successfully!", listOrderRequest);
//    }
//    @GetMapping("/getOrderRequestAudience-reject")
//    public ResponseEntity getOrderRequestDenyAudience(){
//        List<OrderRequest> listOrderRequest = orderRequestService.getOrderRequestAudienceStatus(StatusEnum.REJECT);
//        return  responseHandler.response(200, "Get Order Reject Audience Successfully!", listOrderRequest);
//    }


    @GetMapping("/getAllOrderRequestAudience")
    public ResponseEntity getAllOrderRequestAudience(){
        List<OrderRequest> listOrderRequest = orderRequestService.getAllOrderRequestAudience();
        return  responseHandler.response(200, "Get All Order  Audience Successfully!", listOrderRequest);
    }
    @GetMapping("/getAllOrderRequestCreator")
    public ResponseEntity getAllOrderRequestCreator(){
        List<OrderRequest> listOrderRequest = orderRequestService.getAllOrderRequestCreator();
        return  responseHandler.response(200, "Get All Order Creator Successfully!", listOrderRequest);
    }

    @GetMapping("/getOrderRequestDetail/{id}")
    public ResponseEntity getOrderRequestDetail(@PathVariable long id){
        System.out.println("cc");
        OrderResponseDTO responseDTO = orderRequestService.getOrderRequestDetail(id);
        return  responseHandler.response(200, "Get Order Detail Successfully!", responseDTO);
    }
    @PutMapping ("/demoOrdeRequest")
    public ResponseEntity demoOrdeRequest(@RequestBody DemoOrderRequestDTO demoOrderRequestDTO){
        DemoRequest demoRequest = orderRequestService.demoOrdeRequest(demoOrderRequestDTO);
        return  responseHandler.response(200, "Demo Order Successfully!", demoRequest);
    }

    @PostMapping("/sendProduct")
    public ResponseEntity sendProduct(@RequestBody OrderRequestDTO orderRequestDTO){
        OrderRequest orderRequest = orderRequestService.sendProduct(orderRequestDTO);
        return  responseHandler.response(200, "Order Done Successfully!", orderRequest);
    }

}