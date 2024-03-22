package start.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import start.dto.request.CatygoryRequestDTO;
import start.dto.request.LoginRequestDTO;
import start.dto.response.UserResponseDTO;
import start.entity.Category;
import start.service.CategoryService;
import start.utils.ResponseHandler;

import java.util.List;

@RestController
@SecurityRequirement(name = "api")
public class CategoryController {
    @Autowired
    ResponseHandler responseHandler;

    @Autowired
    CategoryService categoryService;
    @PostMapping("/addCategory")
    public ResponseEntity addCategory(@RequestParam("name") String name){
        Category category = categoryService.addCategory(name);
        return responseHandler.response(200, "Add Category success!", category);
    }

    @GetMapping("/adminCategorys")
    public ResponseEntity getAllCategoryAdmin(){
        List<Category> category = categoryService.getAllCategoryAdmin();
        return responseHandler.response(200, "Get All Category success!", category);
    }
    @GetMapping("/creatorCategorys")
    public ResponseEntity creatorCategorys(){
        List<Category> category = categoryService.creatorCategorys();
        return responseHandler.response(200, "Get All Category Creator success!", category);
    }

    @PutMapping("/UpdateCategory")
    public ResponseEntity updateCategory(@RequestBody CatygoryRequestDTO catygoryRequestDTO){
        Category category = categoryService.updateCategory(catygoryRequestDTO);
        return responseHandler.response(200, "Update Category success!", category);
    }
    @PutMapping("/changeCategory")
    public ResponseEntity changeCategory(@RequestBody CatygoryRequestDTO catygoryRequestDTO){
        Category category = categoryService.changeCategory(catygoryRequestDTO);
        return responseHandler.response(200, "Change Category success!", category);
    }

}
