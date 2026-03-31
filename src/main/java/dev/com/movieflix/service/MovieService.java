package dev.com.movieflix.service;

import dev.com.movieflix.model.CategoryModel;
import dev.com.movieflix.model.MovieModel;
import dev.com.movieflix.model.StreamingModel;
import dev.com.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public MovieModel salvar(MovieModel movie) {
       movie.setCategories(this.acharCategorias(movie.getCategories()));
       movie.setStreamings(this.acharStreaming(movie.getStreamings()));
        return repository.save(movie);
    }

    public List<MovieModel> listarTodos() {
        return repository.findAll();
    }

    private List<CategoryModel> acharCategorias(List<CategoryModel> categories) {
        List<CategoryModel> categoriesFound = new ArrayList<>();
        categories.forEach(category -> categoryService.buscarPorId(category.getId()).ifPresent(categoriesFound::add));
        return categoriesFound;
    }

    private List<StreamingModel> acharStreaming(List<StreamingModel> streamings) {
        List<StreamingModel> streamingsFound = new ArrayList<>();
        streamings.forEach(streaming -> streamingService.buscarPorId(streaming.getId()).ifPresent(streamingsFound::add));
        return streamingsFound;
    }

    public List<MovieModel> buscarPorCategoria(Long categoryId) {
        CategoryModel category = CategoryModel.builder().id(categoryId).build();
        return repository.findMovieByCategoriesIn(List.of(category));
    }

    public Optional<MovieModel> buscarId(Long id) {
        return repository.findById(id);
    }

    public void deletarFilme(Long id) {
        repository.deleteById(id);
    }

   public Optional<MovieModel> atualizar(Long id, MovieModel updateMovie) {
       Optional<MovieModel> optionalMovie = repository.findById(id);
       if (optionalMovie.isPresent()) {

           List<CategoryModel> categories = this.acharCategorias(updateMovie.getCategories());
           List<StreamingModel> streamings = this.acharStreaming(updateMovie.getStreamings());

           MovieModel movie = optionalMovie.get();
           movie.setTitle(updateMovie.getTitle());
           movie.setDescription(updateMovie.getDescription());
           movie.setReleaseDate(updateMovie.getReleaseDate());
           movie.setRating(updateMovie.getRating());

           movie.getCategories().clear();
           movie.getCategories().addAll(categories);

           movie.getStreamings().clear();
           movie.getStreamings().addAll(streamings);

           repository.save(movie);
           return Optional.of(movie);
       }
       return Optional.empty();
   }


}
