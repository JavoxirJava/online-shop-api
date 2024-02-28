package online.shop.online_shop.repository;

import online.shop.online_shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.category.id = ?1")
    List<Product> findAllByCategoryId(Long id);

    @Query("select p.name as name, p.image.id as image, p.price as price from Product p ")
    List<ProductDtos> productList();
    interface ProductDtos {
        String getName();
        String getImage();
        Double getPrice();

    }
}
