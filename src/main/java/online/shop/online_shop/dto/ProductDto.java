package online.shop.online_shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import online.shop.online_shop.entity.Image;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @Schema(hidden = true)
    private Long id;

    @NotNull(message = "Product name cannot be null")
    private String name;

    @NotNull(message = "Product description cannot be null")
    private String description;

    @NotNull(message = "Product weight type cannot be null")
    private Long weightTypeId;

    @NotNull(message = "Product price cannot be null")
    private Double price;

//    @NotNull(message = "Product image cannot be null")
    private Long imageId;

    @NotNull(message = "Product category cannot be null")
    private Long categoryId;
}
