package start.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.dto.request.ApproveRequestDTO;
import start.dto.request.ArtworkRequestDTO;
import start.dto.request.LoginRequestDTO;
import start.dto.response.LoginResponse;
import start.entity.Artwork;
import start.entity.Category;
import start.entity.User;
import start.enums.StatusEnum;
import start.repository.ArtworkRepository;
import start.repository.CategoryRepository;
import start.repository.UserRepository;
import start.utils.AccountUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArtworkService  {
    @Autowired
    AccountUtils accountUtils;
    @Autowired
    ArtworkRepository artworkRepository;
    @Autowired
    CategoryRepository categoryRepository;


    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;

    public Artwork addNewArtwork(ArtworkRequestDTO artworkRequestDTO) {
        Set<Category> listCategoryID = new HashSet<>();
        for (String categoryName : artworkRequestDTO.getCategoriesName()) {
            Category category = categoryRepository.findAllByName(categoryName);
            if (category != null) {
                System.out.println(categoryName);
                listCategoryID.add(category);
            }
        }
        Artwork artwork = new Artwork();
        artwork.setTitle(artworkRequestDTO.getTitle());
        artwork.setImage(artworkRequestDTO.getImage());
        artwork.setCreateDate(artworkRequestDTO.getCreateDate());
        artwork.setDescription(artworkRequestDTO.getDescription());
        artwork.setPrice(artworkRequestDTO.getPrice());
        artwork.setCategories(listCategoryID);
        artwork.setStatus(StatusEnum.PENDING);

        artwork.setUser(accountUtils.getCurrentUser());

        return artworkRepository.save(artwork);
    }



    public List<Artwork> getAllArtWork(){
        List<Artwork> artworkList = artworkRepository.findByStatus(StatusEnum.ACTIVE);
        return artworkList;
    }


    public Artwork getArtwokDetaill(long id) {
        Artwork artwork = artworkRepository.findById(id);
        return artwork;
    }

    public Artwork artworkApprove(long id, ApproveRequestDTO approve) {
        Artwork artwork = artworkRepository.findById(id);
        if(approve.getStatus().toLowerCase().trim().equals("active")){
            artwork.setStatus(StatusEnum.ACTIVE);
        }else{
            artwork.setStatus(StatusEnum.REJECT);
            emailService.sendMail(artwork.getUser(),approve.getDescription());
        }
        return artworkRepository.save(artwork);
    }
}
