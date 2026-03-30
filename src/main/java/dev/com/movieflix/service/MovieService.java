package dev.com.movieflix.service;

import dev.com.movieflix.model.Category;
import dev.com.movieflix.model.Movie;
import dev.com.movieflix.model.Streaming;
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

    public Movie salvar(Movie movie) {
       movie.setCategories(this.acharCategorias(movie.getCategories()));
       movie.setStreamings(this.acharStreaming(movie.getStreamings()));
        return repository.save(movie);
    }

    public List<Movie> listarTodos() {
        return repository.findAll();
    }

    private List<Category> acharCategorias(List<Category> categories) {
        List<Category> categoriesFound = new ArrayList<>();
        categories.forEach(category -> categoryService.buscarPorId(category.getId()).ifPresent(categoriesFound::add));
        return categoriesFound;
    }

    private List<Streaming> acharStreaming(List<Streaming> streamings) {
        List<Streaming> streamingsFound = new ArrayList<>();
        streamings.forEach(streaming -> streamingService.buscarPorId(streaming.getId()).ifPresent(streamingsFound::add));
        return streamingsFound;
    }

    public List<Movie> buscarPorCategoria(Long categoryId) {
        return repository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()));
    }

    public Optional<Movie> buscarId(Long id) {
        return repository.findById(id);
    }

    public void deletarFilme(Long id) {
        repository.deleteById(id);
    }

   public Optional<Movie> atualizar(Long id, Movie updateMovie) {
       Optional<Movie> optionalMovie = repository.findById(id);
       if (optionalMovie.isPresent()) {

           List<Category> categories = this.acharCategorias(updateMovie.getCategories());
           List<Streaming> streamings = this.acharStreaming(updateMovie.getStreamings());

           Movie movie = optionalMovie.get();
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
