package dev.com.movieflix.controller;

import dev.com.movieflix.dto.UserDTO;
import dev.com.movieflix.dto.request.LoginRequest;
import dev.com.movieflix.dto.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Users", description = "Recurso responsavel pelo gerenciamento dos Usuarios da aplicação")
public interface AuthController {

    @Operation(summary = "Salvar Usuarios", description = "Metodo responsavel por cadastrar e salvar novos usuarios no banco de dados")
    @ApiResponse(responseCode = "201", description = "Usuario salvo com sucesso", content = @Content(schema = @Schema(implementation = UserDTO.class)))
    @PostMapping("/cadastrar")
    public ResponseEntity<UserDTO> cadastrarUsuario(@RequestBody UserDTO userDTO);

    @Operation(summary = "Fazer Login", description = "Metodo responsavel por ver se o usuario existe no banco de dados e gerar um token para fazer as requisições das outras classes")
    @ApiResponse(responseCode = "200", description = "Retorna o Token", content = @Content(schema = @Schema(implementation = LoginResponse.class)))
    @ApiResponse(responseCode = "404", description = "Login ou senha invalidos", content = @Content())
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request);

    @Operation(summary = "Busca Usuarios", description = "Metodo responsavel por buscar todos os Usuarios cadastrados no banco de dados",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todos os usuarios cadastrados", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class))))
    @GetMapping("/listar")
    public ResponseEntity<List<UserDTO>> listarTodosUsuarios();

    @Operation(summary = "Deleta todos os Usuarios", description = "Metodo responsavel por deletar todos os usuarios cadastrados no banco de dados",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Usuarios deletados com sucesso", content = @Content())
    @DeleteMapping("/deletar")
    public ResponseEntity<Void> deletarTodos();

}
