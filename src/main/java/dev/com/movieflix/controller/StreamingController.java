package dev.com.movieflix.controller;

import dev.com.movieflix.dto.request.StreamingRequest;
import dev.com.movieflix.dto.response.StreamingResponse;
import dev.com.movieflix.mapper.StreamingMapper;
import dev.com.movieflix.model.Streaming;
import dev.com.movieflix.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService service;

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> listarStreaming() {
        List<StreamingResponse> streaming = service.listarTodos()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();

        return ResponseEntity.ok(streaming);
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> cadastrarStreaming(@RequestBody StreamingRequest request) {
        Streaming newStreming = StreamingMapper.toStreaming(request);
        Streaming saveStreming = service.cadastrarStreaming(newStreming);
        var streaming = StreamingMapper.toStreamingResponse(saveStreming);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(streaming);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Long id) {
        if (service.buscarPorId(id).isPresent()) {
            service.deletarStreaming(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID " + id + " não existe em nosso registros");
        }
    }




}
