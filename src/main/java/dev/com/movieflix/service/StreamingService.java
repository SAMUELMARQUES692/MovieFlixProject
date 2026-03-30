package dev.com.movieflix.service;

import dev.com.movieflix.model.Streaming;
import dev.com.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository repository;

    public List<Streaming> listarTodos() {
        return repository.findAll();
    }

    public Streaming cadastrarStreaming(Streaming streaming) {
        return repository.save(streaming);
    }

    public Optional<Streaming> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletarStreaming(Long id) {
        repository.deleteById(id);
    }

}

