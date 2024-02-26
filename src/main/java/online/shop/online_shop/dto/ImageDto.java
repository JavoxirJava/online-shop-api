package online.shop.online_shop.dto;

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

    private Long id;

    @NotNull(message = "Image name cannot be null")
    private String name;

    @NotNull(message = "Image content type cannot be null")
    private String contentType;

    @NotNull(message = "Image size cannot be null")
    private Long size;

    private Resource resource;
    private Image image;

    public ImageDto(Long id, String name, String contentType, Long size, Resource resource) {
    }
}
