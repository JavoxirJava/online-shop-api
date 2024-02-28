package online.shop.online_shop.controller;

import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.BasketDto;
import online.shop.online_shop.service.BasketService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basket")
public class BasketController {
    final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping("/create")
    public HttpEntity<?> create(@RequestBody BasketDto basketDto){
        ApiResponse<?>  apiResponse = basketService.create(basketDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
}
