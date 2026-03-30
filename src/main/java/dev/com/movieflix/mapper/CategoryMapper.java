package dev.com.movieflix.mapper;

import dev.com.movieflix.dto.request.CategoryRequest;
import dev.com.movieflix.dto.response.CategoryResponse;
import dev.com.movieflix.model.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequest categoryRequest) {
        return Category
                .builder()
                .name(categoryRequest.name())
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

}
