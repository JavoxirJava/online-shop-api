package online.shop.online_shop.service;

import lombok.RequiredArgsConstructor;
import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.ImageDto;
import online.shop.online_shop.entity.Image;
import online.shop.online_shop.exception.GenericNotFoundException;
import online.shop.online_shop.repository.ImageRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageService {

    final ImageRepository imageRepository;

    private static Integer count = 0;

    public static final Path root = Paths.get("src/main/resources/image");


    public ApiResponse<?> upload(MultipartFile file) throws IOException {
        count++;
        Files.copy(file.getInputStream(), root.resolve("a" + count + file.getOriginalFilename()));
        Image image = new Image();
        image.setName("a" + count + file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        return new ApiResponse<>(imageRepository.save(image).getId(), "Successfully", true);
    }

    public ImageDto getFile(Long id) throws MalformedURLException {
        Image image = imageRepository.findById(id).orElseThrow(() ->
                GenericNotFoundException.builder().message("Image id not found").statusCode(404).build());
        Path file = root.resolve(image.getName());
        Resource resource = new UrlResource(file.toUri());
        return new ImageDto(
                image.getId(),
                image.getName(),
                image.getContentType(),
                image.getSize(),
                resource,
                image
        );
    }

    public Long editImage(Long id, MultipartFile file) throws IOException {
        Image image = imageRepository.findById(id).orElseThrow(() ->
                GenericNotFoundException.builder().message("Image id not found").statusCode(404).build());
        Files.copy(file.getInputStream(), root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        image.setName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        return imageRepository.save(image).getId();
    }

    public ApiResponse<?> deleteImage(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(() ->
                GenericNotFoundException.builder().message("Image not found").statusCode(404).build());
        imageRepository.delete(image);
        return new ApiResponse<>("Successfully deleted image", true);
    }

    public Image getOneImage(Long imageId) {
        return imageRepository.findById(imageId).orElseThrow(() ->
                GenericNotFoundException.builder().message("Image not found").statusCode(404).build());
    }
}