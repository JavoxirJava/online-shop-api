package online.shop.online_shop.service;

import lombok.RequiredArgsConstructor;
import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.OrderDto;
import online.shop.online_shop.entity.Order;
import online.shop.online_shop.exception.GenericNotFoundException;
import online.shop.online_shop.repository.OrderRepository;
import online.shop.online_shop.repository.ProductRepository;
import online.shop.online_shop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ApiResponse<?> addOrder(Long userId, Long productId) {
        if (userRepository.findById(userId).isPresent() && productRepository.findById(productId).isPresent()) {
            Order order = new Order();
            order.setProduct(productRepository.findById(productId).get());
            order.setUser(userRepository.findById(userId).get());
            orderRepository.save(order);
            return new ApiResponse<>("Order created", true);
        }else {
            Order order = orderRepository.oneOrder(userId, productId);
            orderRepository.delete(order);
            return new ApiResponse<>("Order deleted", true);
        }

    }



}
