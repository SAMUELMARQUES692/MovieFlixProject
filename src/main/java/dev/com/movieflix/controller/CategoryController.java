package dev.com.movieflix.controller;

import dev.com.movieflix.dto.request.CategoryRequest;
import dev.com.movieflix.dto.response.CategoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Category", description = "Recurso responsavel pelo gerenciamento das categorias dos filmes")
public interface CategoryController {

    @Operation(summary = "Busca Categoria", description = "Metodo responsavel por buscar todas as categorias cadastrados no banco de dados",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todos as categorias cadastrados", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryResponse.class))))
    public ResponseEntity<List<CategoryResponse>> listarCategorias();

    @Operation(summary = "Salvar Categoria", description = "Metodo responsavel por cadastrar e salvar novas categorias no banco de dados")
    @ApiResponse(responseCode = "201", description = "Categoria salva com sucesso", content = @Content(schema = @Schema(implementation = CategoryResponse.class)))
    public ResponseEntity<CategoryResponse> cadastrarCategoria(@Valid @RequestBody CategoryRequest request);

    @Operation(summary = "Busca as Categorias pelo ID", description = "Metodo responsavel por buscar categorias de filmes pelo ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Categoria encontrado com sucesso", content = @Content(schema = @Schema(implementation = CategoryResponse.class)))
    @ApiResponse(responseCode = "404", description = "Categoria não encontrado", content = @Content())
    public ResponseEntity<CategoryResponse> buscarPorId(@PathVariable Long id);

    @Operation(summary = "Deleta categorias por ID", description = "Metodo responsavel por deletar categorias de filmes pelo ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Categoria deletado com sucesso", content = @Content())
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content())
    public ResponseEntity<?> deletarPorId(@PathVariable Long id);

}
