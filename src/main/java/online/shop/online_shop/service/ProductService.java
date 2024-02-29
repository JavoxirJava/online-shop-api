package online.shop.online_shop.service;

import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.ProductDto;
import online.shop.online_shop.entity.Category;
import online.shop.online_shop.entity.Product;
import online.shop.online_shop.exception.GenericNotFoundException;
import online.shop.online_shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    final ProductRepository productRepository;
    final CategoryService categoryService;

    final ImageService imageService;
    final WeightTypeService weightTypeService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, ImageService imageService, WeightTypeService weightTypeService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.imageService = imageService;
        this.weightTypeService = weightTypeService;
    }

    public ProductDto getProduct(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .categoryId(product.getCategory().getId())
                .imageId(product.getImage() != null ? product.getImage().getId() : null)
                .build();
    }

    public ApiResponse<?> addProduct(ProductDto productDto) {
        if (productDto.getCategoryId() != null) {
            Optional<Category> byId = categoryService.getOneCategoryById(productDto.getCategoryId());
            if (byId.isEmpty()) return new ApiResponse<>("Category not found", false);
            else {
                Product product = new Product();
                product.setName(productDto.getName());
                product.setPrice(productDto.getPrice());
                product.setDescription(productDto.getDescription());
                product.setCategory(categoryService.getOneCategory(productDto.getCategoryId()));
                if (productDto.getImageId() == 0) product.setImage(null);
                else  product.setImage(imageService.getOneImage(productDto.getImageId()));
                product.setWeightType(weightTypeService.getOneWeightType(productDto.getWeightTypeId()));
                productRepository.save(product);
                return new ApiResponse<>("Product added", true);
            }
        }
        return new ApiResponse<>("Category id is required", false);
    }

    public ApiResponse<?> getProduct(Long id) {
        return ApiResponse.builder().body(getProduct(getProductOne(id))).message("Successfully get").success(true).build();
    }

    public ApiResponse<?> updateProduct(ProductDto productDto, Long id) {
        Product product = getProductOne(id);
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        if (productDto.getCategoryId() != null)
            product.setCategory(categoryService.getOneCategory(productDto.getCategoryId()));
        if (productDto.getImageId() != null) product.setImage(imageService.getOneImage(productDto.getImageId()));
        productRepository.save(product);
        return new ApiResponse<>("Product updated", true);
    }

    public ApiResponse<?> deleteProduct(Long id) {
        productRepository.deleteById(id);
        return new ApiResponse<>("Product deleted", true);
    }

    public ApiResponse<?> getProductListByCategoryId(Long categoryId) {
        List<Product> allByCategoryId = productRepository.findAllByCategoryId(categoryId);
        return new ApiResponse<>(allByCategoryId, "Success", true);
    }

    public ApiResponse<?> getProductsList() {
        List<ProductRepository.ProductDtos> productDtos = productRepository.productList();
        return new ApiResponse<>(productDtos, "Success", true);
    }

    public Product getProductOne(Long id) {
        return productRepository.findById(id).orElseThrow(()
                -> GenericNotFoundException.builder().message("Product not found").statusCode(404).build());
    }
}
