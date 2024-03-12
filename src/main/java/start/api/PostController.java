package start.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import start.dto.request.PostRequestDTO;
import start.dto.response.BuyArtworkResponseDTO;
import start.dto.response.UserResponseDTO;
import start.entity.Transaction;
import start.service.PostService;
import start.utils.ResponseHandler;

import java.util.UUID;

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
        return  responseHandler.response(200, "Buy Post Successfully!", userResponseDTO);
    }

        @PostMapping("/buyArtwork/{id}")
    public ResponseEntity buyArtwork(@PathVariable long id){
        BuyArtworkResponseDTO artwork = postService.buyArtwork(id);
        return  responseHandler.response(200, "Buy Artwork Successfully!", artwork);
    }

    @PostMapping("/checkOut/{id}")
    public ResponseEntity checkOut(@PathVariable UUID id){
        Transaction transaction = postService.checkOut(id);
        return  responseHandler.response(200, "Check Out Artwork Successfully!", transaction);
    }

}
