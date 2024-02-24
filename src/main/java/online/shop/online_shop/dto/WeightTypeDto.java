package online.shop.online_shop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WeightTypeDto {

    private Long id;

    @NotNull(message = "Weight type name cannot be null")
    private String name;
}
