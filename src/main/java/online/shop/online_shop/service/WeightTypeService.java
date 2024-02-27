package online.shop.online_shop.service;


import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.WeightTypeDto;
import online.shop.online_shop.entity.WeightType;
import online.shop.online_shop.exception.GenericNotFoundException;
import online.shop.online_shop.repository.WeightTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class WeightTypeService {
    private final WeightTypeRepository weightTypeRepository;

    public WeightTypeService(WeightTypeRepository weightTypeRepository) {
        this.weightTypeRepository = weightTypeRepository;
    }

    public ApiResponse<?> addWeightType(WeightTypeDto weightTypeDto) {
        if (weightTypeRepository.existsByName(weightTypeDto.getName())) {
            return new ApiResponse<>("Weight type already exists", false);
        }
        WeightType weightType = new WeightType();
        weightType.setName(weightTypeDto.getName());
        weightTypeRepository.save(weightType);
        return new ApiResponse<>("Weight type added", true);
    }

    public ApiResponse<?> getWeightType(Long id){
        WeightType weightType = weightTypeRepository.findById(id).orElseThrow(() ->
                GenericNotFoundException.builder().message("Not found").statusCode(404).build());
        return ApiResponse.builder().body(weightType).message("Success").success(true).build();
    }

    public ApiResponse<?> updateWeightType(WeightTypeDto weightTypeDto){
        WeightType weightType = weightTypeRepository.findById(weightTypeDto.getId()).orElseThrow(() ->
                GenericNotFoundException.builder().message("Not found").statusCode(404).build());
        weightType.setName(weightTypeDto.getName());
        weightTypeRepository.save(weightType);
        return new ApiResponse<>("Weight type updated", true);
    }

    public ApiResponse<?> deleteWeightType(Long id){
        WeightType weightType = weightTypeRepository.findById(id).orElseThrow(() ->
                GenericNotFoundException.builder().message("Not found").statusCode(404).build());
        weightTypeRepository.delete(weightType);
        return new ApiResponse<>("Weight type deleted", true);
    }
}