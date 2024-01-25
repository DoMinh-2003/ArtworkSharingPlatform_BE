package start.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import start.dto.request.ArtworkRequestDTO;
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

    @PostMapping("/artwork")
    public ResponseEntity addArtwork(@RequestBody ArtworkRequestDTO artworkRequestDTO){
       Artwork artwork = artworkService.addNewArtwork(artworkRequestDTO);
        return  responseHandler.response(200, "Added Artwork Successlly!", artwork);
    }

    @GetMapping("/artworks")
    public ResponseEntity getAllArtwok(){
        return  responseHandler.response(200, "Get All Artwork Successlly!", artworkService.getAllArtWork());
    }



}
