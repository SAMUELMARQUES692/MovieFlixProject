package dev.com.movieflix.controller;

import dev.com.movieflix.dto.request.CategoryRequest;
import dev.com.movieflix.dto.response.CategoryResponse;
import dev.com.movieflix.mapper.CategoryMapper;
import dev.com.movieflix.model.CategoryModel;
import dev.com.movieflix.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController{

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> listarCategorias() {
        List<CategoryResponse> categories = service.listarTodos()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> cadastrarCategoria(@Valid @RequestBody CategoryRequest request) {
        CategoryModel newCategory = CategoryMapper.toCategory(request);
        CategoryModel saveCategory = service.cadastrarCategoria(newCategory);
        var categories = CategoryMapper.toCategoryResponse(saveCategory);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Long id) {
        if (service.buscarPorId(id).isPresent()) {
            service.deletarCategoria(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID " + id + " não existe em nosso registros");
        }
    }

}
