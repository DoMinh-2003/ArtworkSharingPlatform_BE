package start.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.dto.request.ArtworkRequestDTO;
import start.entity.Artwork;
import start.entity.Category;
import start.repository.ArtworkRepository;
import start.repository.CategoryRepository;

import java.util.HashSet;
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
        return artworkRepository.save(artwork);
    }

    public List<Artwork> getAllArtWork(){
        List<Artwork> artworkList = artworkRepository.findAll();
        return artworkList;
    }

}
