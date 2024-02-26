package online.shop.online_shop.repository;

import online.shop.online_shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
