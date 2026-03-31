package dev.com.movieflix.service;

import dev.com.movieflix.dto.UserDTO;
import dev.com.movieflix.mapper.UserMapper;
import dev.com.movieflix.model.UserModel;
import dev.com.movieflix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserDTO cadastrarUsuario(UserDTO userDTO) {
        UserModel user = mapper.map(userDTO);
        user = repository.save(user);
        return mapper.map(user);
    }

    public List<UserDTO> listarUsuarios() {
         List<UserModel> user = repository.findAll();
         return user.stream()
                 .map(mapper::map)
                 .collect(Collectors.toList());
    }

}
