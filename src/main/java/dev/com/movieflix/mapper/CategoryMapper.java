package dev.com.movieflix.mapper;

import dev.com.movieflix.dto.request.CategoryRequest;
import dev.com.movieflix.dto.response.CategoryResponse;
import dev.com.movieflix.model.CategoryModel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static CategoryModel toCategory(CategoryRequest categoryRequest) {
        return CategoryModel
                .builder()
                .name(categoryRequest.name())
                .build();
    }

    public static CategoryResponse toCategoryResponse(CategoryModel category) {
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

}
