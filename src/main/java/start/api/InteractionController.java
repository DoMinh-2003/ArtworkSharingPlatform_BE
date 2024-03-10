package start.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import start.dto.request.InteractionRequestDTO;
import start.entity.Interaction;
import start.service.InteractionService;
import start.utils.ResponseHandler;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class InteractionController {
    @Autowired
    ResponseHandler responseHandler;

    @Autowired
    InteractionService interactionService;

    @PostMapping("/send-interaction")
    public ResponseEntity<Interaction> sendInteraction(@RequestBody InteractionRequestDTO interactionRequestDTO){
        Interaction interaction = interactionService.sendInteraction(interactionRequestDTO);
        return responseHandler.response(200, "Send Interaction Successfully!", interaction);
    }

    @PutMapping("/disLike")
    public ResponseEntity<Interaction> disLike(@RequestBody InteractionRequestDTO interactionRequestDTO){
        Interaction interaction = interactionService.disLike(interactionRequestDTO);
        return responseHandler.response(200, "DisLike Interaction Successfully!", interaction);
    }


}
