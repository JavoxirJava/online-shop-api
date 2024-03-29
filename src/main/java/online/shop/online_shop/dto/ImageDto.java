package online.shop.online_shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import online.shop.online_shop.entity.Image;
import org.springframework.core.io.Resource;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {
    @Schema(hidden = true)
    private Long id;

    @NotNull(message = "Image name cannot be null")
    private String name;

    @NotNull(message = "Image content type cannot be null")
    private String contentType;

    @NotNull(message = "Image size cannot be null")
    private Long size;

    private Resource resource;
    private Image image;
}
