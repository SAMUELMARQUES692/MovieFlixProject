package dev.com.movieflix.repository;

import dev.com.movieflix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
