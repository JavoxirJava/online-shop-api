package online.shop.online_shop.controller;

import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.WeightTypeDto;
import online.shop.online_shop.service.WeightTypeService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/weightType")
public class WeightTypeController {

    private final WeightTypeService weightTypeService;
    public WeightTypeController(WeightTypeService weightTypeService) {
        this.weightTypeService = weightTypeService;
    }

    @PostMapping
    public HttpEntity<?> addWeightType(@RequestBody WeightTypeDto weightTypeDto) {
        ApiResponse<?> apiResponse = weightTypeService.addWeightType(weightTypeDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/get/{id}")
    public HttpEntity<?> getWeightType(@PathVariable Long id){
        ApiResponse<?> apiResponse = weightTypeService.getWeightType(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/update")
    public HttpEntity<?> updateWeightType(@RequestBody WeightTypeDto weightTypeDto){
        ApiResponse<?> apiResponse = weightTypeService.updateWeightType(weightTypeDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteWeightType(@PathVariable Long id){
        ApiResponse<?> apiResponse = weightTypeService.deleteWeightType(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
