package start.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name ="api")

public class AIController {
    @GetMapping("/getKeyApi")
    public ResponseEntity getAllArtwok(){
        return  ResponseEntity.ok("xILU1ELRYRR3zdvuUnSrT3BlbkFJ1XzW4xHVRf4Pt8w0UQn3");
    }
}
