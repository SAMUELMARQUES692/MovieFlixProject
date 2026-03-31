package dev.com.movieflix.service;

import dev.com.movieflix.dto.UserDTO;
import dev.com.movieflix.mapper.UserMapper;
import dev.com.movieflix.model.User;
import dev.com.movieflix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserDTO cadastrarUsuario(UserDTO userDTO) {
        User user = mapper.map(userDTO);
        user = repository.save(user);
        return mapper.map(user);
    }
}
