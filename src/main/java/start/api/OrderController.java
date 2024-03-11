package start.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import start.dto.request.RechargeRequestDTO;

import start.entity.Wallet;
import start.service.WalletService;
import start.utils.AccountUtils;
import start.utils.ResponseHandler;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

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


    @PutMapping("/recharge/{id}")
    public ResponseEntity recharge(@PathVariable UUID id) throws Exception {
        Wallet wallet = walletService.recharge(id);
        return  responseHandler.response(200, "Recharge Successfully!", wallet);
    }




}
