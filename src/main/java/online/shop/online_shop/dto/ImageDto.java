package online.shop.online_shop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ImageDto {

    private Long id;

    @NotNull(message = "Image name cannot be null")
    private String name;

    @NotNull(message = "Image content type cannot be null")
    private String contentType;

    @NotNull(message = "Image size cannot be null")
    private Long size;
}
