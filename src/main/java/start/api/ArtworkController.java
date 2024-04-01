package start.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import start.dto.request.ApproveRequestDTO;
import start.dto.request.ArtworkRequestDTO;
import start.dto.request.ListCategoryNameDTO;
import start.dto.response.ArtworkResponseDTO;
import start.dto.response.LoginResponse;
import start.entity.Artwork;
import start.entity.User;
import start.enums.RoleEnum;
import start.enums.StatusEnum;
import start.service.ArtworkService;
import start.utils.ResponseHandler;

import java.util.List;
import java.util.UUID;

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
        return  responseHandler.response(200, "Get All Artwork Pending Successlly!", artworkService.getAllArtWork("pending"));
    }

    @GetMapping("/artworkByCreator-pending")
    public ResponseEntity getAllArtwokPendingByCreator(){
        return  responseHandler.response(200, "Get a Successful Creator's Pending Artwork!", artworkService.getAllArtwokStatusByCreator(StatusEnum.PENDING));
    }

    @GetMapping("/artworkByCreator-reject")
    public ResponseEntity getAllArtwokRejectByCreator(){
        return  responseHandler.response(200, "Get a Successful Creator's Reject Artwork!", artworkService.getAllArtwokStatusByCreator(StatusEnum.REJECT));
    }

    @GetMapping("/artworkByCreator-active")
    public ResponseEntity getAllArtwokActiveByCreator(){
        return  responseHandler.response(200, "Get a Successful Creator's Active Artwork!", artworkService.getAllArtwokStatusByCreator(StatusEnum.ACTIVE));
    }
    @GetMapping("/artworkByCreator-cancel")
    public ResponseEntity getAllArtwokCancelByCreator(){
        return  responseHandler.response(200, "Get a Successful Creator's Cancel Artwork!", artworkService.getAllArtwokStatusByCreator(StatusEnum.CANCEL));
    }


    @GetMapping("/artworkActiveByCreator/{id}")
    public ResponseEntity ArtwokActiveByCreator(@PathVariable UUID id){
        return  responseHandler.response(200, "Get a Successful Creator's Artwork!", artworkService.artwokStatusByCreator(id,StatusEnum.ACTIVE));
    }


    @GetMapping("/artwork-detail/{id}")
    public ResponseEntity getArtwokDetaill(@PathVariable long id){
        ArtworkResponseDTO artwork = artworkService.getArtwokDetaill(id);
        return  responseHandler.response(200, "Get Artwork Detail Successlly!", artwork);
    }

    @PutMapping("/artwork-approve/{id}")
    public ResponseEntity confirmArtwork(@PathVariable long id, @RequestBody ApproveRequestDTO approve){
        Artwork artwork = artworkService.artworkApprove(id, approve);
        return  responseHandler.response(200, "Artwork Approve Successlly!", artwork);
    }
    @PutMapping("/deleteArtwork/{id}")
    public ResponseEntity deleteArtwork(@PathVariable long id){
        Artwork artwork = artworkService.deleteArtwork(id);
        return  responseHandler.response(200, "Delete Artwork Successlly!", artwork);
    }
    @PutMapping("/updateArtwork/{id}")
    public ResponseEntity updateArtwork(@PathVariable long id, @RequestBody ArtworkRequestDTO artworkRequestDTO){
        Artwork artwork = artworkService.updateArtwork(id,artworkRequestDTO);
        return  responseHandler.response(200, "Update Artwork Successlly!", artwork);
    }

    @GetMapping("/searchArtwork")
    public ResponseEntity searchArtwork(@RequestParam("search") String search){
        List<ArtworkResponseDTO> artworks = artworkService.searchArtwork(search);
        return  responseHandler.response(200, "Get Search Artwork Successfully!", artworks);
    }

    @PostMapping("/artworkByCategory")
    public ResponseEntity artworkByCategory(@RequestBody ListCategoryNameDTO listCategoryNameDTO){
        List<String> categoryName = listCategoryNameDTO.getCategoryName();
        List<ArtworkResponseDTO> artworks = artworkService.artworkByCategory(categoryName);
        return  responseHandler.response(200, "Get Search Artwork By Category Successfully!", artworks);
    }

}