package online.shop.online_shop.service;

import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.ProductDto;
import online.shop.online_shop.entity.Product;
import online.shop.online_shop.repository.CategoryRepository;
import online.shop.online_shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    final ProductRepository productRepository;
    final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    public ApiResponse<?> addProduct(ProductDto productDto, Long id) {
        if (categoryRepository.findById(id).isEmpty()) {
            return new ApiResponse<>("Category not found", false);
        }else {
            Product product = new Product();
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            product.setCategory(categoryRepository.findById(id).get());
            productRepository.save(product);
            return new ApiResponse<>("Product added", true);
        }
    }


}
