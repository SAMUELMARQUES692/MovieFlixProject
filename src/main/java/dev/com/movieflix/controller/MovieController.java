package dev.com.movieflix.controller;

import dev.com.movieflix.dto.request.MovieRequest;
import dev.com.movieflix.dto.response.MovieResponse;
import dev.com.movieflix.mapper.MovieMapper;
import dev.com.movieflix.model.Movie;
import dev.com.movieflix.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movieflix/movie")
public class MovieController {

    private final MovieService service;

    @PostMapping
    public ResponseEntity<MovieResponse> salvar(@RequestBody MovieRequest request) {
       Movie savedMovie = service.salvar(MovieMapper.toMovie(request));
        var saveConvertido = MovieMapper.toMovieResponse(savedMovie);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saveConvertido);

    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> listarFilmes() {
        List<MovieResponse> movies = service.listarTodos()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> buscarFilmePorID(@PathVariable Long id) {
       return service.buscarId(id)
               .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
               .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarFilmePorId(@PathVariable Long id) {
        if (service.buscarId(id).isPresent()) {
            service.deletarFilme(id);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID " + id  + " não existe em nosso registros");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> atualizarFilmePorID(@PathVariable Long id, @RequestBody MovieRequest movieRequest) {
        return service.atualizar(id, MovieMapper.toMovie(movieRequest))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<MovieResponse>> buscarPorCategoria(@RequestParam Long category) {
       List<MovieResponse> list = service.buscarPorCategoria(category)
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok(list);
    }










}
