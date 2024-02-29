package online.shop.online_shop.controller;

import online.shop.online_shop.dto.BasketDto;
import online.shop.online_shop.entity.User;
import online.shop.online_shop.security.CurrentUser;
import online.shop.online_shop.service.BasketService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basket")
public class BasketController {
    final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @PostMapping
    public HttpEntity<?> create(@RequestBody BasketDto basketDto, @CurrentUser User user) {
        return ResponseEntity.ok(basketService.create(basketDto, user.getId()));
    }

    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@RequestBody BasketDto basketDto, @PathVariable Long id) {
        return ResponseEntity.ok(basketService.editBasket(basketDto, id));
    }

    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(basketService.deleteBasket(id));
    }

    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @GetMapping("/{id}")
    public HttpEntity<?> getBasket(@PathVariable Long id) {
        return ResponseEntity.ok(basketService.getBasket(id));
    }

    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @GetMapping("/all")
    public HttpEntity<?> getAllBaskets(@CurrentUser User user) {
        return ResponseEntity.ok(basketService.getAllBaskets(user.getId()));
    }
}
