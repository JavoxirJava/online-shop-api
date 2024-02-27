package online.shop.online_shop.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryDto {
    @Schema(hidden = true)
    private Long id;
    @NotNull(message = "Category name cannot be null")
    private String name;
}
