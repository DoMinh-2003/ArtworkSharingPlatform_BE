package start.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import start.dto.request.InteractionRequestDTO;
import start.entity.Artwork;
import start.entity.Interaction;
import start.entity.User;
import start.enums.TypeEnum;
import start.exception.exceptions.NotLikeException;
import start.repository.ArtworkRepository;
import start.repository.InteractionRepository;
import start.utils.AccountUtils;

import java.util.List;

@Service
public class InteractionService {


    @Autowired
    InteractionRepository interactionRepository;

    @Autowired
    AccountUtils accountUtils;

    @Autowired
    ArtworkRepository artworkRepository;

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    public Interaction sendInteraction(InteractionRequestDTO interactionRequestDTO) {
            Interaction interaction = new Interaction();
            User user =  accountUtils.getCurrentUser();
            Artwork artwork = artworkRepository.findById(interactionRequestDTO.getArtworkId());
            if(interactionRequestDTO.getType().toLowerCase().trim().equals("like")){
                Interaction checkInteraction = interactionRepository.findByUser_IdAndArtwork_IdAndType(user.getId(),artwork.getId(),TypeEnum.LIKE);
                Interaction checkInteractionDislike = interactionRepository.findByUser_IdAndArtwork_IdAndType(user.getId(),artwork.getId(),TypeEnum.DISLIKE);
                if(checkInteractionDislike == null){
                    if(checkInteraction == null){
                        interaction.setContent(interactionRequestDTO.getContent());
                        interaction.setCreateDate(interactionRequestDTO.getCreateDate());
                        interaction.setType(TypeEnum.LIKE);
                        interaction.setUser(user);
                        interaction.setArtwork(artwork);
                    }else{
                        throw new NotLikeException("dislike");
                    }
                }else{
                    interaction.setId(checkInteractionDislike.getId());
                    interaction.setContent(checkInteractionDislike.getContent());
                    interaction.setCreateDate(checkInteractionDislike.getCreateDate());
                    interaction.setType(TypeEnum.LIKE);
                    interaction.setUser(checkInteractionDislike.getUser());
                    interaction.setArtwork(checkInteractionDislike.getArtwork());
                }

            }else{
                interaction.setContent(interactionRequestDTO.getContent());
                interaction.setCreateDate(interactionRequestDTO.getCreateDate());
                interaction.setType(TypeEnum.COMMENT);
                interaction.setUser(user);
                interaction.setArtwork(artwork);

            }
            messagingTemplate.convertAndSend("/topic/interaction", "interaction");

        return interactionRepository.save(interaction);
    }

    public Interaction disLike(InteractionRequestDTO interactionRequestDTO) {
        User user =  accountUtils.getCurrentUser();
        Interaction checkInteraction = interactionRepository.findByUser_IdAndArtwork_IdAndType(user.getId(),interactionRequestDTO.getArtworkId(),TypeEnum.LIKE);
        checkInteraction.setType(TypeEnum.DISLIKE);
        messagingTemplate.convertAndSend("/topic/interaction", "interaction");
        return interactionRepository.save(checkInteraction);
    }


    public List<Interaction> likeShot() {
        User user = accountUtils.getCurrentUser();
        return interactionRepository.findByUser_IdAndType(user.getId(),TypeEnum.LIKE);
    }
}
