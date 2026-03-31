package dev.com.movieflix.mapper;

import dev.com.movieflix.dto.UserDTO;
import dev.com.movieflix.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public record UserMapper() {

    public UserModel map(UserDTO userDTO) {

        UserModel user = new UserModel();
        user.setId(userDTO.id());
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());

        return user;
    }

    public UserDTO map(UserModel user) {

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword());
    }
}
