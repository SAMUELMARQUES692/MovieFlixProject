package dev.com.movieflix.controller;

import dev.com.movieflix.config.TokenService;
import dev.com.movieflix.dto.UserDTO;
import dev.com.movieflix.dto.request.LoginRequest;
import dev.com.movieflix.dto.response.LoginResponse;
import dev.com.movieflix.model.UserModel;
import dev.com.movieflix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/cadastrar")
    public ResponseEntity<UserDTO> cadastrarUsuario(@RequestBody UserDTO userDTO) {
        UserDTO novoUsuario = service.cadastrarUsuario(userDTO);
        return ResponseEntity.ok(novoUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authenticate = authenticationManager.authenticate(userAndPass);

        UserModel userModel = (UserModel)authenticate.getPrincipal();

       String token = tokenService.generateToken(userModel);

       return ResponseEntity.ok(new LoginResponse(token));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UserDTO>> listarTodosUsuarios() {
        List<UserDTO> users = service.listarUsuarios();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<Void> deletarTodos() {
        service.deleteAll();
        return ResponseEntity.noContent().build();
    }




}
