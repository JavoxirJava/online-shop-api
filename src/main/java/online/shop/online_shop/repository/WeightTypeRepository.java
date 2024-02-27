package online.shop.online_shop.repository;

import online.shop.online_shop.entity.WeightType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightTypeRepository extends JpaRepository<WeightType, Long> {
    boolean existsByName(String name);
}
