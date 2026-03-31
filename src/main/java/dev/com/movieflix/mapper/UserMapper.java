package dev.com.movieflix.mapper;

import dev.com.movieflix.dto.UserDTO;
import dev.com.movieflix.model.User;
import org.springframework.stereotype.Component;

@Component
public record UserMapper() {

    public User map(UserDTO userDTO) {

        User user = new User();
        user.setId(userDTO.id());
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());

        return user;
    }

    public UserDTO map(User user) {

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword());
    }
}
