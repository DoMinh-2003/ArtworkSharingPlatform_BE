package start.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.dto.request.ArtworkRequestDTO;
import start.entity.Artwork;
import start.entity.Category;
import start.repository.ArtworkRepository;
import start.repository.CategoryRepository;
import start.repository.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArtworkService  {
    @Autowired
    ArtworkRepository artworkRepository;
    @Autowired
    CategoryRepository categoryRepository;
    public Artwork addNewArtwork(ArtworkRequestDTO artworkRequestDTO) {
        Artwork artwork = new Artwork();
        artwork.setName(artworkRequestDTO.getName());
        artwork.setImage(artworkRequestDTO.getImage());
        artwork.setDescription(artworkRequestDTO.getDescription());
        artwork.setPrice(artworkRequestDTO.getPrice());
        Set<Category> listCategory = categoryRepository.findAllById(artworkRequestDTO.getCategoriesID()).stream().collect(Collectors.toSet());
        artwork.setCategories(listCategory);
        return artworkRepository.save(artwork);
    }

    public List<Artwork> getAllArtWork(){
        List<Artwork> artworkList = artworkRepository.findAll();
        return artworkList;
    }

}
