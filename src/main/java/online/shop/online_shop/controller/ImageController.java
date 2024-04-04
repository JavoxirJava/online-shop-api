package online.shop.online_shop.controller;

import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.ImageDto;
import online.shop.online_shop.service.ImageService;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@CrossOrigin
@RequestMapping("/attachment")
@RequiredArgsConstructor
public class ImageController {
    final ImageService imageService;

    @ApiParam(allowMultiple = true)
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public HttpEntity<?> upload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        ApiResponse<?> upload = imageService.upload(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(upload);
    }
    @GetMapping("/getFile/{id}")
    public HttpEntity<?> getFile(@PathVariable Long id) throws MalformedURLException {
        ImageDto file = imageService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(file.getImage().getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                        + file.getName())
                .body(file.getResource());
    }

    @PreAuthorize("hasAnyRole( 'ROLE_ADMIN')")
    @ApiParam(allowMultiple = true)
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public Long editFile(@PathVariable Long id, @RequestParam(value = "file") MultipartFile file) throws IOException {
        return imageService.editImage(id, file);
    }

    @PreAuthorize("hasAnyRole( 'ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteAttachment(@PathVariable Long id) {
        ApiResponse<?> apiResponse = imageService.deleteImage(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
    }
}