package online.shop.online_shop.service;

import lombok.RequiredArgsConstructor;
import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.OrderDto;
import online.shop.online_shop.entity.Order;
import online.shop.online_shop.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    public ApiResponse<?> addOrder(OrderDto orderDto) {
        Order order = Order.builder().build();
        order.setId(orderDto.getId());
        return new ApiResponse<>("Order created", true);
    }

}
