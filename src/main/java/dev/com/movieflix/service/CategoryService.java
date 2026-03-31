package dev.com.movieflix.service;

import dev.com.movieflix.model.CategoryModel;
import dev.com.movieflix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<CategoryModel> listarTodos() {
       return repository.findAll();
    }

    public CategoryModel cadastrarCategoria(CategoryModel category) {
        return repository.save(category);
    }

    public Optional<CategoryModel> buscarPorId(Long id) {
       return repository.findById(id);
    }

    public void deletarCategoria(Long id) {
        repository.deleteById(id);
    }

}
