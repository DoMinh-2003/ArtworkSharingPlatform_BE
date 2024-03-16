package start.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.dto.request.ApproveRequestDTO;
import start.dto.request.ArtworkRequestDTO;
import start.dto.response.ArtworkResponseDTO;
import start.entity.Artwork;
import start.entity.Category;
import start.entity.Interaction;
import start.entity.User;
import start.enums.StatusEnum;
import start.enums.TypeEnum;
import start.repository.ArtworkRepository;
import start.repository.CategoryRepository;
import start.repository.UserRepository;
import start.utils.AccountUtils;

import java.util.*;
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
        User user = accountUtils.getCurrentUser();
//        User userResponse = userRepository.findUserById(user.getId());
        if(artworkRequestDTO.getPrice()> 0){
            if(user.getPostQuantity() >0){
                user.setPostQuantity(user.getPostQuantity() -1);
                user = userRepository.save(user);
            } else{
                throw new RuntimeException("You don't have enough posts left");
            }

        }
        artwork.setUser(user);
        return artworkRepository.save(artwork);
    }



    public List<ArtworkResponseDTO> getAllArtWork(String status){
        List<Artwork> artworkList = artworkRepository.findByStatus(status.toLowerCase().trim().equals("active")?StatusEnum.ACTIVE:StatusEnum.PENDING);
        List<ArtworkResponseDTO> listArtwork = new ArrayList<>();
        for(Artwork artwork:artworkList){
            int countLike = 0;
            int countComment = 0;
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
            for(Interaction interaction: artwork.getInteractions()){
                if(interaction.getType().equals(TypeEnum.LIKE)){
                    countLike += 1;
                }else if (interaction.getType().equals(TypeEnum.COMMENT)){
                    countComment += 1;
                }
            }
            artworkResponseDTO.setCountLike(countLike);
            artworkResponseDTO.setCountComment(countComment);
            artworkResponseDTO.setInteractionLike( artwork.getInteractions().stream().filter(aw -> aw.getType().equals(TypeEnum.LIKE)).collect(Collectors.toSet()));
            artworkResponseDTO.setInteractionComment( artwork.getInteractions().stream().filter(aw -> aw.getType().equals(TypeEnum.COMMENT)).collect(Collectors.toSet()));
            listArtwork.add(artworkResponseDTO);
        }
        return listArtwork;
    }

    public List<Artwork> getAllArtwokStatusByCreator( StatusEnum status){
        User user =  accountUtils.getCurrentUser();
        List<Artwork> artworkList = artworkRepository.findByUserIdAndStatus(user.getId(),status);
        List<ArtworkResponseDTO> listArtwork = new ArrayList<>();
        for(Artwork artwork:artworkList){
            int countLike = 0;
            int countComment = 0;
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
            for(Interaction interaction: artwork.getInteractions()){
                if(interaction.getType().equals(TypeEnum.LIKE)){
                    countLike += 1;
                }else if (interaction.getType().equals(TypeEnum.COMMENT)){
                    countComment += 1;
                }
            }
            artworkResponseDTO.setCountLike(countLike);
            artworkResponseDTO.setCountComment(countComment);
            artworkResponseDTO.setInteractionLike( artwork.getInteractions().stream().filter(aw -> aw.getType().equals(TypeEnum.LIKE)).collect(Collectors.toSet()));
            artworkResponseDTO.setInteractionComment( artwork.getInteractions().stream().filter(aw -> aw.getType().equals(TypeEnum.COMMENT)).collect(Collectors.toSet()));
            listArtwork.add(artworkResponseDTO);
        }

        return artworkList;
    }


    public List<ArtworkResponseDTO> artwokStatusByCreator(UUID id, StatusEnum status){
        List<Artwork> artworkList = artworkRepository.findByUserIdAndStatus(id,status);
        List<ArtworkResponseDTO> listArtwork = new ArrayList<>();
        for(Artwork artwork:artworkList){
            int countLike = 0;
            int countComment = 0;
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
            for(Interaction interaction: artwork.getInteractions()){
                if(interaction.getType().equals(TypeEnum.LIKE)){
                    countLike += 1;
                }else if (interaction.getType().equals(TypeEnum.COMMENT)){
                    countComment += 1;
                }
            }
            artworkResponseDTO.setCountLike(countLike);
            artworkResponseDTO.setCountComment(countComment);
            artworkResponseDTO.setInteractionLike( artwork.getInteractions().stream().filter(aw -> aw.getType().equals(TypeEnum.LIKE)).collect(Collectors.toSet()));
            artworkResponseDTO.setInteractionComment( artwork.getInteractions().stream().filter(aw -> aw.getType().equals(TypeEnum.COMMENT)).collect(Collectors.toSet()));
            listArtwork.add(artworkResponseDTO);
        }
        return listArtwork;


    }
    public ArtworkResponseDTO getArtwokDetaill(long id) {
        int countLike = 0;
        int countComment = 0;
        Artwork artwork = artworkRepository.findById(id);
        ArtworkResponseDTO artworkResponseDTO = new ArtworkResponseDTO();
        if (artwork.getStatus().equals(StatusEnum.ACTIVE)){
            artworkResponseDTO.setId(artwork.getId());
            artworkResponseDTO.setTitle(artwork.getTitle());
            artworkResponseDTO.setImage(artwork.getImage());
            artworkResponseDTO.setDescription(artwork.getDescription());
            artworkResponseDTO.setCreateDate(artwork.getCreateDate());
            artworkResponseDTO.setPrice(artwork.getPrice());
            artworkResponseDTO.setStatus(artwork.getStatus());
            artworkResponseDTO.setUser(artwork.getUser());
            artworkResponseDTO.setCategories(artwork.getCategories());
            for(Interaction interaction: artwork.getInteractions()){
                if(interaction.getType().equals(TypeEnum.LIKE)){
                    countLike += 1;
                }else if (interaction.getType().equals(TypeEnum.COMMENT)){
                    countComment += 1;
                }
            }
            artworkResponseDTO.setCountLike(countLike);
            artworkResponseDTO.setCountComment(countComment);
            artworkResponseDTO.setInteractionLike( artwork.getInteractions().stream().filter(aw -> aw.getType().equals(TypeEnum.LIKE)).collect(Collectors.toSet()));
            artworkResponseDTO.setInteractionComment( artwork.getInteractions().stream().filter(aw -> aw.getType().equals(TypeEnum.COMMENT)).collect(Collectors.toSet()));
        }
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
