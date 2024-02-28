package online.shop.online_shop.repository;

import online.shop.online_shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from orders o where o.user.id = ?1 and o.product.id = ?2")
    Order oneOrder(Long userId, Long productId);
}
