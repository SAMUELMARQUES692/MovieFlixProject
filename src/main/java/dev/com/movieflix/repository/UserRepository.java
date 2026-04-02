package dev.com.movieflix.repository;

import dev.com.movieflix.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserDetails> findUserByEmail(String email);

}
