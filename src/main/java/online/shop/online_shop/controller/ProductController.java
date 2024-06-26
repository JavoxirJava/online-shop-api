package online.shop.online_shop.controller;

import jakarta.validation.Valid;
import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.ProductDto;
import online.shop.online_shop.service.ProductService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PreAuthorize("hasAnyRole( 'ROLE_ADMIN')")
    @PostMapping("/add")
    public HttpEntity<?> addProduct(@RequestBody @Valid ProductDto productDto) {
        ApiResponse<?> apiResponse = productService.addProduct(productDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getProduct(@PathVariable Long id) {
        ApiResponse<?> apiResponse = productService.getProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getProductList() {
        ApiResponse<?> apiResponse = productService.getProductsList();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/search/{text}")
    public HttpEntity<?> searchProduct(@PathVariable String text) {
        return ResponseEntity.ok(productService.searchProduct(text));
    }

    @PreAuthorize("hasAnyRole( 'ROLE_ADMIN')")
    @PutMapping("/{id}")
    public HttpEntity<?> updateProduct(@RequestBody @Valid ProductDto productDto, @PathVariable Long id) {
        ApiResponse<?> apiResponse = productService.updateProduct(productDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize("hasAnyRole( 'ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProduct(@PathVariable Long id) {
        ApiResponse<?> apiResponse = productService.deleteProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/by/category/{id}")
    public HttpEntity<?> getProductListByCategoryId(@PathVariable Long id) {
        ApiResponse<?> apiResponse = productService.getProductListByCategoryId(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
