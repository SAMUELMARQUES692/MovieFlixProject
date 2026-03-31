package dev.com.movieflix.repository;

import dev.com.movieflix.model.CategoryModel;
import dev.com.movieflix.model.MovieModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieModel, Long> {

    List<MovieModel> findMovieByCategoriesIn(List<CategoryModel> categories);

}
