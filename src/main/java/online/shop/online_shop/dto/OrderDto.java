package online.shop.online_shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    @Schema(hidden = true)
    private Long id;
    @NotNull(message = "Product id cannot be null")
    private Long productId;
    @NotNull(message = "Quantity cannot be null")
    private Long quantity;
    @NotNull(message = "User id cannot be null")
    private Long userId;
}
