package start.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import start.dto.request.RechargeRequestDTO;


import start.entity.Wallet;

import start.service.WalletService;
import start.utils.ResponseHandler;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class OrderController {


    @Autowired
    ResponseHandler responseHandler;

    @Autowired
    WalletService walletService;

    @PostMapping("/request-recharge-vnpay")
    public ResponseEntity createUrl(@RequestBody RechargeRequestDTO rechargeRequestDTO) throws Exception {
       String url = walletService.createUrl(rechargeRequestDTO);
            return  responseHandler.response(200, "Create Url Successfully!", url);

    }

    @PostMapping("/request-recharge-paypal")
    public ResponseEntity createPaypalPayment(@RequestBody RechargeRequestDTO rechargeRequestDTO) throws Exception {
       String url = walletService.createPaypalPayment(rechargeRequestDTO);
        return  responseHandler.response(200, "Create Url Successfully!", url);
    }


    @PutMapping("/recharge/{id}")
    public ResponseEntity recharge(@PathVariable UUID id) throws Exception {
        Wallet wallet = walletService.recharge(id);
        return  responseHandler.response(200, "Recharge Successfully!", wallet);
    }




}
