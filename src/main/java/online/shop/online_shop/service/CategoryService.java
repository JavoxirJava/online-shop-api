package online.shop.online_shop.service;

import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.CategoryDto;
import online.shop.online_shop.entity.Category;
import online.shop.online_shop.exception.GenericNotFoundException;
import online.shop.online_shop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ApiResponse<?> addCategory(CategoryDto categoryDto) {
        if (categoryRepository.existsByName(categoryDto.getName()))
            return new ApiResponse<>("Category already exist", false);
        else {
            Category category = new Category();
            category.setName(categoryDto.getName());
            categoryRepository.save(category);
            return new ApiResponse<>("Category added", true);
        }
    }

    public ApiResponse<?> updateCategory(CategoryDto categoryDto) {
        if (!categoryRepository.existsById(categoryDto.getId()))
            return new ApiResponse<>("Category not found", false);
        else {
            Category category = getOneCategory(categoryDto.getId());
            category.setName(categoryDto.getName());
            categoryRepository.save(category);
            return new ApiResponse<>("Category updated", true);
        }
    }

    public ApiResponse<?> getAllCategories() {
        return new ApiResponse<>(categoryRepository.findAll(), "", true);
    }

    public ApiResponse<?> deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return new ApiResponse<>("Category deleted", true);
    }

    public ApiResponse<?> getCategoryById(Long id) {
        return ApiResponse.builder().body(getOneCategory(id)).build();
    }

    public Category getOneCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(()
                -> GenericNotFoundException.builder().message("not found").statusCode(404).build());
    }
}
