package dev.com.movieflix.controller;

import dev.com.movieflix.dto.request.MovieRequest;
import dev.com.movieflix.dto.response.MovieResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Movie", description = "Recurso responsavel pelo gerenciamento dos filmes")
public interface MovieController {

    @Operation(summary = "Salvar Filme", description = "Metodo responsavel por cadastrar e salvar novos filmes no banco de dados")
    @ApiResponse(responseCode = "201", description = "Filme salvo com sucesso", content = @Content(schema = @Schema(implementation = MovieResponse.class)))
     ResponseEntity<MovieResponse> salvar(@Valid @RequestBody MovieRequest request);



    @Operation(summary = "Busca Filme", description = "Metodo responsavel por buscar todos os filmes cadastrados no banco de dados",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todos os filmes cadastrados", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
     ResponseEntity<List<MovieResponse>> listarFilmes();



    @Operation(summary = "Busca Filme pelo ID", description = "Metodo responsavel por buscar filmes pelo ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme encontrado com sucesso", content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())
     ResponseEntity<MovieResponse> buscarFilmePorID(@PathVariable Long id);



    @Operation(summary = "Atualiza os filmes por ID", description = "Metodo responsavel por atualizar os filmes",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso", content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())
    ResponseEntity<MovieResponse> atualizarFilmePorID(@PathVariable Long id,@Valid @RequestBody MovieRequest movieRequest);



    @Operation(summary = "Busca Filme por categoria", description = "Metodo responsavel por buscar filmes por categoria",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme encontrado com sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())
     ResponseEntity<List<MovieResponse>> buscarPorCategoria(@RequestParam Long category);



    @Operation(summary = "Deleta filme por ID", description = "Metodo responsavel por deletar filmes pelo ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Filme deletado com sucesso", content = @Content())
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())
    ResponseEntity<?> deletarFilmePorId(@PathVariable Long id);

}
