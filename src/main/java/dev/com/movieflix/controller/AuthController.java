package dev.com.movieflix.controller;

import dev.com.movieflix.dto.UserDTO;
import dev.com.movieflix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<UserDTO> cadastrarUsuario(@RequestBody UserDTO userDTO) {
        UserDTO novoUsuario = service.cadastrarUsuario(userDTO);
        return ResponseEntity.ok(novoUsuario);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UserDTO>> listarTodosUsuarios() {
        List<UserDTO> users = service.listarUsuarios();
        return ResponseEntity.ok(users);
    }




}
