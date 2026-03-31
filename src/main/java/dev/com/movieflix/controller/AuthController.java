package dev.com.movieflix.controller;

import dev.com.movieflix.dto.UserDTO;
import dev.com.movieflix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserDTO> cadastrarUsuario(@RequestBody UserDTO userDTO) {
        UserDTO novoUsuario = service.cadastrarUsuario(userDTO);
        return ResponseEntity.ok(novoUsuario);
    }




}
