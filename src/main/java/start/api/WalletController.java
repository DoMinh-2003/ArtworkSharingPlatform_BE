package start.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import start.dto.request.RechargeRequestDTO;
import start.dto.request.WithDrawRequestDTO;
import start.dto.response.TransactionResponseDTO;
import start.entity.Wallet;
import start.service.WalletService;
import start.utils.ResponseHandler;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class WalletController {


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
    @PostMapping("/withDraw")
    public ResponseEntity withDraw(@RequestBody WithDrawRequestDTO withDrawRequestDTO) throws Exception {
        Wallet wallet = walletService.withDraw(withDrawRequestDTO);
        return  responseHandler.response(200, "Send Request Successfully!", wallet);
    }
    @GetMapping("/requestWithDraw")
    public ResponseEntity requestWithDraw() throws Exception {
        List<TransactionResponseDTO> list = walletService.requestWithDraw();
        return  responseHandler.response(200, "Send Request Successfully!", list);
    }

    @GetMapping("/walletDetail/{id}")
    public ResponseEntity walletDetail(@PathVariable UUID id) throws Exception {
        Wallet wallet = walletService.walletDetail(id);
        return  responseHandler.response(200, "Get waller detail successfully!", wallet);

    }

}
