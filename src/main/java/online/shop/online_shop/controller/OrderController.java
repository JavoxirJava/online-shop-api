package online.shop.online_shop.controller;

import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.OrderDto;
import online.shop.online_shop.entity.User;
import online.shop.online_shop.security.CurrentUser;
import online.shop.online_shop.service.OrderService;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @PostMapping("/liked/{productId}")
    public HttpEntity<?> liked(@CurrentUser User user , @PathVariable Long productId){
        ApiResponse<?> apiResponse = orderService.addOrder(user.getId(), productId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }
}
