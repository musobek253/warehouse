package uz.pdp.warehouse.cantroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.tamplet.Category;
import uz.pdp.warehouse.entity.tamplet.Warehouse;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.payload.CategoryDto;
import uz.pdp.warehouse.repositary.CategoryRepository;
import uz.pdp.warehouse.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping("/add")
    public ApiResponse addCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.addCategory(categoryDto);
    }

    @PutMapping("/{id}")
    public ApiResponse editCategory(@PathVariable Integer id,@RequestBody CategoryDto categoryDto){
        return categoryService.editCategory(id, categoryDto);
    }

    @GetMapping("/parentnull")
    public List<Category> getCategory(){
        return categoryService.getParentIdNull();
    }

    @GetMapping("/{id}")
    public Category getByIdCategory(@PathVariable Integer id){
        return categoryService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deletedCategory(@PathVariable Integer id){
        return categoryService.deletedCategory(id);
    }

}
