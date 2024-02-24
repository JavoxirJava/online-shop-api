package online.shop.online_shop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDto {

    private Long id;

    @NotNull(message = "Product name cannot be null")
    private String name;

    @NotNull(message = "Product description cannot be null")
    private String description;

    @NotNull(message = "Product weight type cannot be null")
    private Long weightTypeId;

    @NotNull(message = "Product price cannot be null")
    private Double price;

    @NotNull(message = "Product category cannot be null")
    private Long categoryId;
}
