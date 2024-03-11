package start.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import start.dto.request.PostRequestDTO;
import start.dto.response.UserResponseDTO;
import start.service.PostService;
import start.utils.ResponseHandler;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class PostController {
@Autowired
    PostService postService;
@Autowired
    ResponseHandler responseHandler;
    @PostMapping("/buyPost")
    public ResponseEntity buyPost(@RequestBody PostRequestDTO postRequestDTO){
        UserResponseDTO userResponseDTO = postService.buyPost(postRequestDTO);
        return  responseHandler.response(200, "Create Url Successfully!", userResponseDTO);
    }

}
