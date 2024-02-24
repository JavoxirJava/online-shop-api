package online.shop.online_shop.service;

import online.shop.online_shop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


}
