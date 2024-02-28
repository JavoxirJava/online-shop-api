package online.shop.online_shop.controller;

import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.BasketDto;
import online.shop.online_shop.service.BasketService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@RequestBody BasketDto basketDto,@PathVariable Long id){
        ApiResponse<?>  apiResponse = basketService.editBasket(basketDto, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        ApiResponse<?>  apiResponse = basketService.deleteBasket(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping("/get/{id}")
    public HttpEntity<?> getBasket(@PathVariable Long id){
        ApiResponse<?>  apiResponse = basketService.getBasket(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
