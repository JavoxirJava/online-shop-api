package online.shop.online_shop.repository;

import online.shop.online_shop.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {

        @Query("select b from Basket b where b.userId = ?1")
        List<Basket> findAllByUserId(Long userId);
}
