package start.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import start.dto.request.UserRequestDTO;
import start.dto.response.UserResponseDTO;
import start.entity.User;
import start.service.UserService;
import start.utils.ResponseHandler;

import java.util.List;
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
        UserResponseDTO user = userService.getCreatorDetail(id);
        return  responseHandler.response(200, "Get Creator detail Successfully!", user);
    }

    @PutMapping("/editProfile")
    public ResponseEntity editProfile(@RequestBody UserRequestDTO userRequestDTO){
        User user = userService.editProfile(userRequestDTO);
        return  responseHandler.response(200, "Edit Profile Successfully!", user);
    }

    @PutMapping("/editPassword")
    public ResponseEntity editPassword(@RequestBody UserRequestDTO userRequestDTO){
        User user = userService.editPassword(userRequestDTO);
        return  responseHandler.response(200, "Edit Password Successfully!", user);
    }

    @PutMapping("/goCreator")
    public ResponseEntity goCreator(){
        User user = userService.goCreator();
        return  responseHandler.response(200, "Go Creator Successfully!", user);
    }





}
