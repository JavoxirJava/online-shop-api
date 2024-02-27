package online.shop.online_shop.service;

import lombok.RequiredArgsConstructor;
import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.ProductDto;
import online.shop.online_shop.entity.Product;
import online.shop.online_shop.exception.GenericNotFoundException;
import online.shop.online_shop.repository.CategoryRepository;
import online.shop.online_shop.repository.ProductRepository;
import online.shop.online_shop.repository.WeightTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final WeightTypeRepository weightTypeRepository;


    public ApiResponse<?> addProduct(ProductDto productDto) {
        if (categoryRepository.findById(productDto.getCategoryId()).isEmpty()) {
            return new ApiResponse<>("Category not found", false);
        }else {
            Product product = new Product();
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            product.setCategory(categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() ->  GenericNotFoundException.builder().message("not found").statusCode(404).build()));
            product.setWeightType(weightTypeRepository.findById(productDto.getWeightTypeId())
                .orElseThrow(() -> GenericNotFoundException.builder().message("Not found").statusCode(404).build()));
            productRepository.save(product);
            return new ApiResponse<>("Product successfully added", true);
        }
    }


}
