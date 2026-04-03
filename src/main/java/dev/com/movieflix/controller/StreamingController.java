package dev.com.movieflix.controller;

import dev.com.movieflix.dto.request.StreamingRequest;
import dev.com.movieflix.dto.response.StreamingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Streaming", description = "Recurso responsavel pelo gerenciamento dos Serviços de Streaming")
public interface StreamingController {

    @Operation(summary = "Busca Serviços de Streaming", description = "Metodo responsavel por buscar todos os serviços de streaming cadastrados no banco de dados",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todas as streaming cadastradas", content = @Content(array = @ArraySchema(schema = @Schema(implementation = StreamingResponse.class))))
    public ResponseEntity<List<StreamingResponse>> listarStreaming();

    @Operation(summary = "Salvar Serviços de Streaming", description = "Metodo responsavel por cadastrar e salvar novos serviços de streaming no banco de dados")
    @ApiResponse(responseCode = "201", description = "Streaming salvo com sucesso", content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    public ResponseEntity<StreamingResponse> cadastrarStreaming(@Valid @RequestBody StreamingRequest request);

    @Operation(summary = "Busca Serviços de Streaming pelo ID", description = "Metodo responsavel por buscar serviços de streaming pelo ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Streaming encontrado com sucesso", content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    @ApiResponse(responseCode = "404", description = "Streaming não encontrado", content = @Content())
    public ResponseEntity<StreamingResponse> buscarPorId(@PathVariable Long id);

    @Operation(summary = "Deleta Streaming por ID", description = "Metodo responsavel por deletar Streaming pelo ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Streaming deletado com sucesso", content = @Content())
    @ApiResponse(responseCode = "404", description = "Streaming não encontrado", content = @Content())
    public ResponseEntity<?> deletarPorId(@PathVariable Long id);

}
