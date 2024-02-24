package online.shop.online_shop.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryDto {
    private Long id;

    @NotNull(message = "Category name cannot be null")
    private String name;
}
