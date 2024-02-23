package start.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import start.dto.request.ArtworkRequestDTO;
import start.dto.response.LoginResponse;
import start.entity.Artwork;
import start.service.ArtworkService;
import start.utils.ResponseHandler;

@RestController
@SecurityRequirement(name ="api")
public class ArtworkController {

    @Autowired
    ArtworkService artworkService;
    @Autowired
    ResponseHandler responseHandler;

    @PostMapping("/postArtwork")
    public ResponseEntity addArtwork(@RequestBody ArtworkRequestDTO artworkRequestDTO){
       Artwork artwork = artworkService.addNewArtwork(artworkRequestDTO);
        return  responseHandler.response(200, "Added Artwork Successlly!", artwork);
    }

    @GetMapping("/artworks")
    public ResponseEntity getAllArtwok(){
        return  responseHandler.response(200, "Get All Artwork Successlly!", artworkService.getAllArtWork("active"));
    }
    @GetMapping("/artworks-pending")
    public ResponseEntity getAllArtwokPending(){
        return  responseHandler.response(200, "Get All Artwork Successlly!", artworkService.getAllArtWork("pending"));
    }
    @GetMapping("/artwork-detail/{id}")
    public ResponseEntity getArtwokDetaill(@PathVariable long id){
        Artwork artwork = artworkService.getArtwokDetaill(id);
        return  responseHandler.response(200, "Get Artwork Detail Successlly!", artwork);
    }

    @PutMapping("/artwork-approve/{id}")
    public ResponseEntity confirmArtwork(@PathVariable long id, @RequestBody String status){
        Artwork artwork = artworkService.artworkApprove(id, status);
        return  responseHandler.response(200, "Artwork Approve Successlly!", artwork);
    }
}
