package dev.com.movieflix.repository;

import dev.com.movieflix.model.Category;
import dev.com.movieflix.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findMovieByCategoriesIn(List<Category> categories);

}
