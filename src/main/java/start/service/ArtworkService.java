package start.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.dto.request.ApproveRequestDTO;
import start.dto.request.ArtworkRequestDTO;
import start.dto.response.ArtworkResponseDTO;
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



    public List<Artwork> getAllArtWork(String status){
        List<Artwork> artworkList = artworkRepository.findByStatus(status.toLowerCase().trim().equals("active")?StatusEnum.ACTIVE:StatusEnum.PENDING);
        return artworkList;
    }

    public List<Artwork> getAllArtwokStatusByCreator(StatusEnum status){
        User user = accountUtils.getCurrentUser();
        List<Artwork> artworkList = artworkRepository.findByUserIdAndStatus(user.getId(),status);
        return artworkList;
    }

    public ArtworkResponseDTO getArtwokDetaill(long id) {
        Artwork artwork = artworkRepository.findById(id);
        ArtworkResponseDTO artworkResponseDTO = new ArtworkResponseDTO();
        artworkResponseDTO.setId(artwork.getId());
        artworkResponseDTO.setTitle(artwork.getTitle());
        artworkResponseDTO.setImage(artwork.getImage());
        artworkResponseDTO.setDescription(artwork.getDescription());
        artworkResponseDTO.setCreateDate(artwork.getCreateDate());
        artworkResponseDTO.setPrice(artwork.getPrice());
        artworkResponseDTO.setStatus(artwork.getStatus());
        artworkResponseDTO.setUser(artwork.getUser());
        artworkResponseDTO.setCategories(artwork.getCategories());
        return artworkResponseDTO;
    }

    public void threadSendMail(User user,String subject, String description){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                emailService.sendMail(user,subject,description);
            }

        };
        new Thread(r).start();
    }

    public Artwork artworkApprove(long id, ApproveRequestDTO approve) {
        Artwork artwork = artworkRepository.findById(id);
        if(approve.getStatus().toLowerCase().trim().equals("active")){
            artwork.setStatus(StatusEnum.ACTIVE);
            threadSendMail(artwork.getUser(),"Your Article " + artwork.getTitle() + " Has Been Approved","Thank you for trusting us to use cremo");
        }else{
            artwork.setStatus(StatusEnum.REJECT);
            artwork.setReasonReject(approve.getDescription());
            threadSendMail(artwork.getUser(),"Reason For Rejecting The Post " + artwork.getTitle(),approve.getDescription());
        }
        return artworkRepository.save(artwork);
    }


    public Artwork deleteArtwork(long id) {
        Artwork artwork = artworkRepository.findById(id);
        User user = accountUtils.getCurrentUser();
        if(user.getId().equals(artwork.getUser().getId())) {
            artwork.setStatus(StatusEnum.CANCEL);
        }
        return artworkRepository.save(artwork);
    }


    public Artwork updateArtwork(long id, ArtworkRequestDTO artworkRequestDTO) {
        Artwork artwork = artworkRepository.findById(id);
        User user = accountUtils.getCurrentUser();
        if(user.getId().equals(artwork.getUser().getId())) {
            artwork.setDescription(artworkRequestDTO.getDescription());
            artwork.setTitle(artworkRequestDTO.getTitle());
        }
        return artworkRepository.save(artwork);
    }
}
