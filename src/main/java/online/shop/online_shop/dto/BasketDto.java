package online.shop.online_shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BasketDto {

    @Schema(hidden = true)
    private Long id;

    @NotNull(message = "Product id can not be null")
    private Long productId;

    @NotNull(message = "User id can not be null")
    private Long userId;
}
