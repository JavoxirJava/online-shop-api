package online.shop.online_shop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.CategoryDto;
import online.shop.online_shop.service.CategoryService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;


    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public HttpEntity<?> addCategory( @Valid  @RequestBody CategoryDto categoryDto) {
        ApiResponse<?> apiResponse = categoryService.addCategory(categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public HttpEntity<?> updateCategory(@RequestBody CategoryDto categoryDto) {
        ApiResponse<?> apiResponse = categoryService.updateCategory(categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/all")
    public HttpEntity<?> getAllCategories() {
        ApiResponse<?> apiResponse = categoryService.getAllCategories();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteCategory(@PathVariable Long id) {
        ApiResponse<?> apiResponse = categoryService.deleteCategory(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/get/{id}")
    public HttpEntity<ApiResponse<?>> getCategoryById(@PathVariable Long id) {
        ApiResponse<?> apiResponse = categoryService.getCategoryById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
