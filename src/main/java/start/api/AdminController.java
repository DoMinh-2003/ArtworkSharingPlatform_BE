package start.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import start.dto.response.MemberToTalResponseDTO;
import start.dto.response.ProfitResponseDTO;
import start.service.AdminService;
import start.utils.ResponseHandler;

import java.util.List;


@RestController
@SecurityRequirement(name ="api")
public class AdminController {

    @Autowired
    AdminService adminService;
    @Autowired
    ResponseHandler responseHandler;

    @GetMapping("/countUser")
    public ResponseEntity countUser(){
        MemberToTalResponseDTO memberToTalResponseDTO = adminService.countUser();
        return  responseHandler.response(200, "Get Count User Successfully!", memberToTalResponseDTO);
    }


    @GetMapping("/ProfitByMonth")
    public ResponseEntity getProfitByMonth(@RequestParam("year") int year){
        List<ProfitResponseDTO> revenuePortal = adminService.getProfitByMonth(year);
        return  responseHandler.response(200, "Get ProfitByMonth Successfully!", revenuePortal);
    }


}
