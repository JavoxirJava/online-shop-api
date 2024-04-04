package online.shop.online_shop.controller;

import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.WeightTypeDto;
import online.shop.online_shop.service.WeightTypeService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/weightType")
public class WeightTypeController {

    private final WeightTypeService weightTypeService;

    public WeightTypeController(WeightTypeService weightTypeService) {
        this.weightTypeService = weightTypeService;
    }

    @PreAuthorize("hasAnyRole( 'ROLE_ADMIN')")
    @PostMapping
    public HttpEntity<?> addWeightType(@RequestBody WeightTypeDto weightTypeDto) {
        ApiResponse<?> apiResponse = weightTypeService.addWeightType(weightTypeDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getWeightTypeList() {
        return ResponseEntity.ok(weightTypeService.getWeightTypeList());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getWeightType(@PathVariable Long id) {
        return ResponseEntity.ok(weightTypeService.getWeightType(id));
    }

    @PreAuthorize("hasAnyRole( 'ROLE_ADMIN')")
    @PutMapping("/{id}")
    public HttpEntity<?> updateWeightType(@RequestBody WeightTypeDto weightTypeDto, @PathVariable Long id) {
        ApiResponse<?> apiResponse = weightTypeService.updateWeightType(weightTypeDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize("hasAnyRole( 'ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteWeightType(@PathVariable Long id) {
        ApiResponse<?> apiResponse = weightTypeService.deleteWeightType(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
