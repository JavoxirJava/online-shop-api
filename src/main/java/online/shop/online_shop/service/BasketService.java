package online.shop.online_shop.service;

import lombok.RequiredArgsConstructor;
import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.BasketDto;
import online.shop.online_shop.entity.Basket;
import online.shop.online_shop.repository.BasketRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;


    public ApiResponse<?> create(BasketDto basketDto) {
        Basket basket = Basket.builder()
                .id(basketDto.getId())
                .productId(basketDto.getProductId())
                .quantity(basketDto.getQuantity())
                .userId(basketDto.getUserId())
                .build();
        basketRepository.save(basket);
        return new ApiResponse<>("Basket created", true);
    }

}
