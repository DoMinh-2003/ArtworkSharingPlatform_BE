package start.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import start.dto.request.ArtworkRequestDTO;
import start.entity.Artwork;
import start.entity.User;
import start.service.UserService;
import start.utils.ResponseHandler;

import java.util.UUID;

@RestController
@SecurityRequirement(name ="api")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ResponseHandler responseHandler;

    @GetMapping("/getCreator-detail/{id}")
    public ResponseEntity getCreatorDetail(@PathVariable UUID id){
        User user = userService.getCreatorDetail(id);
        return  responseHandler.response(200, "Get Creator detail Successlly!", user);
    }
}
