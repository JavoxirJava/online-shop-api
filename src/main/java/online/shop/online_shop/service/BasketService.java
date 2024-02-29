package online.shop.online_shop.service;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.BasketDto;
import online.shop.online_shop.entity.Basket;
import online.shop.online_shop.exception.GenericNotFoundException;
import online.shop.online_shop.repository.BasketRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;


    public ApiResponse<?> create(BasketDto basketDto, Long userId) {
        basketDto.setUserId(userId);
        basketRepository.save(basketDto(basketDto, new Basket()));
        return new ApiResponse<>("Basket created", true);
    }

    public ApiResponse<?> getBasket(Long id){
        return new ApiResponse<>(getBasketById(id), "Successfully", true);
    }

    public ApiResponse<?> deleteBasket(Long id) {
        basketRepository.deleteById(id);
        return new ApiResponse<>("Basket deleted", true);
    }

    public ApiResponse<?> editBasket(BasketDto basketDto, Long id) {
        basketRepository.save(basketDto(basketDto, getBasketById(id)));
        return new ApiResponse<>("Basket edited", true);
    }

    public Basket basketDto(BasketDto basketDto, Basket basket){
        basket.setProductId(basketDto.getProductId());
        basket.setQuantity(basketDto.getQuantity());
        basket.setUserId(basketDto.getUserId());
        return basket;
    }

    public Basket getBasketById(Long id){
        return basketRepository.findById(id).orElseThrow(() ->
                GenericNotFoundException.builder().message("Basket id not found").statusCode(404).build());
    }

    public ApiResponse<?> getAllBaskets(Long userId) {
        return new ApiResponse<>(basketRepository.findAllByUserId(userId), "Successfully", true);
    }

}
