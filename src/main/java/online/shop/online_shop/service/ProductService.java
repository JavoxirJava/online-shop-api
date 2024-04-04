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

    public List<Product> searchProduct(String text) {
        return productRepository.searchProduct(text);
    }

    public ProductDto getProduct(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .categoryId(product.getCategory().getId())
                .weightTypeId(product.getWeightType().getId())
                .imageId(product.getImage() != null ? product.getImage().getId() : null)
                .build();
    }

    public ApiResponse<?> addProduct(ProductDto productDto) {
        if (productDto.getWeightTypeId() == null) return new ApiResponse<>("Weight type id is required", false);
        if (productDto.getCategoryId() == null) return new ApiResponse<>("Category id is required", false);
        Optional<Category> byId = categoryService.getOneCategoryById(productDto.getCategoryId());
        if (byId.isEmpty()) return new ApiResponse<>("Category not found", false);
        else {
            Product product = new Product();
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            product.setImage(productDto.getImageId() == 0 ? null : imageService.getOneImage(productDto.getImageId()));
            product.setCategory(categoryService.getOneCategory(productDto.getCategoryId()));
            product.setWeightType(weightTypeService.getOneWeightType(productDto.getWeightTypeId()));
            productRepository.save(product);
            return new ApiResponse<>("Product added", true);
        }
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
