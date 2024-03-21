package start.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import start.dto.response.MemberToTalResponseDTO;
import start.service.AdminService;
import start.utils.ResponseHandler;


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

    @GetMapping("/revenuePortal")
    public ResponseEntity revenuePortal(){
        float  revenuePortal = adminService.revenuePortal();
        return  responseHandler.response(200, "Get Revenue Portal Successfully!", revenuePortal);
    }

    @GetMapping("/ProfitByMonth")
    public ResponseEntity getProfitByMonth(@RequestParam("month") int month,@RequestParam("year") int year){
        float  revenuePortal = adminService.getProfitByMonth(month, year);
        return  responseHandler.response(200, "Get ProfitByMonth Successfully!", revenuePortal);
    }


}
