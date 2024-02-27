package online.shop.online_shop.service;

import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.ProductDto;
import online.shop.online_shop.entity.Category;
import online.shop.online_shop.entity.Product;
import online.shop.online_shop.repository.CategoryRepository;
import online.shop.online_shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    final ProductRepository productRepository;
    final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ApiResponse<?> addProduct(ProductDto productDto) {
        if (productDto.getCategoryId() != null) {
            Optional<Category> byId = categoryRepository.findById(productDto.getCategoryId());
            if (byId.isEmpty()) return new ApiResponse<>("Category not found", false);
            else {
                Product product = new Product();
                product.setName(productDto.getName());
                product.setPrice(productDto.getPrice());
                product.setDescription(productDto.getDescription());
                product.setCategory(categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found")));
                productRepository.save(product);
                return new ApiResponse<>("Product added", true);
            }
        }
        return new ApiResponse<>("Category not found", false);
    }


}
