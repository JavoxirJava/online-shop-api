package online.shop.online_shop.controller;

import jakarta.validation.Valid;
import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.ProductDto;
import online.shop.online_shop.service.ProductService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }

    @PostMapping("/add")
    public HttpEntity<?> addProduct(@RequestBody @Valid ProductDto productDto) {
        ApiResponse<?> apiResponse = productService.addProduct(productDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }
}
