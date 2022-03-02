package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import uz.pdp.warehouse.entity.tamplet.Category;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.payload.CategoryDto;
import uz.pdp.warehouse.repositary.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ApiResponse addCategory(CategoryDto categoryDto){
        Category category = new Category();
        if (categoryRepository.existsByName(categoryDto.getName()))
            return new ApiResponse("Already exist name",false);
        category.setName(categoryDto.getName());
        if (categoryDto.getCategoryParenId() != null){
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getCategoryParenId());
            if (!optionalCategory.isPresent())
                return new ApiResponse("There is not this parent category",false);
            category.setParentCategoryId(categoryRepository.findById(categoryDto.getCategoryParenId()).get());
        }
        categoryRepository.save(category);
        return new ApiResponse("Successfully added",true);
    }

    public ApiResponse editCategory(Integer id,CategoryDto categoryDto){
        Optional<Category> optional = categoryRepository.findById(id);
        if (!optional.isPresent())
            return new ApiResponse("Category not found",false);
        Category category = optional.get();
        if (categoryRepository.existsByNameNot(categoryDto.getName()))
            return new ApiResponse("Already exist name",false);
        if (categoryDto.getCategoryParenId() != null){
            Optional<Category> optionalParent = categoryRepository.findById(categoryDto.getCategoryParenId());
            if (!optional.isPresent())
                return new ApiResponse("There is not this parent category",false);
            category.setParentCategoryId(optionalParent.get());
        }
        category.setName(categoryDto.getName());
        return new ApiResponse("Successfully edited",true);
    }

    // parent id null

    public List<Category> getParentIdNull(){
        return categoryRepository.findAllByParentCategoryIdNull();
    }

    public Category getById(Integer id){
        Optional<Category> byId = categoryRepository.findById(id);
        return byId.orElseGet(Category::new);
    }
    public ApiResponse deletedCategory(Integer id){
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()){
            categoryRepository.deleteById(id);
            return new ApiResponse("Successfully deleted",true);
        }
        return new ApiResponse("deleted eror",false);
    }
}
