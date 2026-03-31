package dev.com.movieflix.mapper;

import dev.com.movieflix.dto.request.MovieRequest;
import dev.com.movieflix.dto.response.CategoryResponse;
import dev.com.movieflix.dto.response.MovieResponse;
import dev.com.movieflix.dto.response.StreamingResponse;
import dev.com.movieflix.model.CategoryModel;
import dev.com.movieflix.model.MovieModel;
import dev.com.movieflix.model.StreamingModel;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public static MovieModel toMovie(MovieRequest request) {

        List<CategoryModel> categories = request.categories().stream()
                .map(categoryId -> CategoryModel.builder().id(categoryId).build())
                .toList();

        List<StreamingModel> streamings = request.streamings().stream()
                .map(streamingId -> StreamingModel.builder().id(streamingId).build())
                .toList();

        return MovieModel.builder()
                .title(request.title())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .rating(request.rating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public static MovieResponse toMovieResponse(MovieModel movie) {

        List<CategoryResponse> categories = movie.getCategories()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

        List<StreamingResponse> streamings = movie.getStreamings()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();

        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }



}
