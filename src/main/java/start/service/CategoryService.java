package start.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import start.dto.request.CatygoryRequestDTO;
import start.entity.Category;
import start.enums.CategoryEnum;
import start.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category addCategory(String name) {
        Category category = new Category();
        List<Category> categories = categoryRepository.findAll();
        for(Category list :categories){
            if(list.getName().equals(name.trim().toLowerCase())){
                throw new RuntimeException("Duplicate Category");
            }
        }
        category.setName(name.trim().toLowerCase());
        category.setCategoryEnum(CategoryEnum.ACTIVE);
        try{
            return categoryRepository.save(category);
        }catch (Exception e) {
            throw new RuntimeException("Duplicate Category");
        }
    }

    public List<Category> getAllCategoryAdmin() {
       return categoryRepository.findAll();
    }


    public Category updateCategory(CatygoryRequestDTO catygoryRequestDTO) {
        Category category = categoryRepository.findById(catygoryRequestDTO.getId());
        List<Category> categories = categoryRepository.findAll();
        for(Category list :categories){
            if(list.getName().equals(catygoryRequestDTO.getName().trim().toLowerCase())){
                throw new RuntimeException("Duplicate Category");
            }
        }
        category.setName(catygoryRequestDTO.getName().trim().toLowerCase());
        try{
            return categoryRepository.save(category);
        }catch (Exception e) {
            throw new RuntimeException("Duplicate Category");
        }
    }


    public List<Category> creatorCategorys() {
        return categoryRepository.findByCategoryEnum(CategoryEnum.ACTIVE);
    }

    public Category changeCategory(CatygoryRequestDTO catygoryRequestDTO) {
        Category category = categoryRepository.findById(catygoryRequestDTO.getId());
        if(catygoryRequestDTO.getStatus().trim().toLowerCase().equals("disable")){
            category.setCategoryEnum(CategoryEnum.REMOVE);
        }else{
            category.setCategoryEnum(CategoryEnum.ACTIVE);
        }
        return categoryRepository.save(category);
    }
}
