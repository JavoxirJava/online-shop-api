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


    public ApiResponse<?> create(BasketDto basketDto) {
        Basket basket = basketDto(basketDto, new Basket());
        basketRepository.save(basket);
        return new ApiResponse<>("Basket created", true);
    }

    public ApiResponse<?> getBasket(Long id){
        Basket basket = basketRepository.findById(id).orElseThrow(() ->
                GenericNotFoundException.builder().message("Basket id not found").statusCode(404).build());
        return new ApiResponse<>(basket, "Successfully", true);
    }

    public ApiResponse<?> deleteBasket(Long id) {
        basketRepository.deleteById(id);
        return new ApiResponse<>("Basket deleted", true);
    }

    public ApiResponse<?> editBasket(BasketDto basketDto, Long id) {
        Basket basket = basketRepository.findById(id).orElseThrow(() ->
                GenericNotFoundException.builder().message("Basket id not found").statusCode(404).build());
        Basket editBasket = basketDto(basketDto, basket);
        basketRepository.save(editBasket);
        return new ApiResponse<>("Basket edited", true);
    }

    public Basket basketDto(BasketDto basketDto, Basket basket){
        basket.setProductId(basketDto.getProductId());
        basket.setQuantity(basketDto.getQuantity());
        basket.setUserId(basketDto.getUserId());
        return basket;
    }

}
