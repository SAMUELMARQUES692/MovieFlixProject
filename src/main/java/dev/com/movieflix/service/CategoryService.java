package dev.com.movieflix.service;

import dev.com.movieflix.model.Category;
import dev.com.movieflix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> listarTodos() {
       return repository.findAll();
    }

    public Category cadastrarCategoria(Category category) {
        return repository.save(category);
    }

    public Optional<Category> buscarPorId(Long id) {
       return repository.findById(id);
    }

    public void deletarCategoria(Long id) {
        repository.deleteById(id);
    }

}
