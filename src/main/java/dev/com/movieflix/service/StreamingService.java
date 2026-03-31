package dev.com.movieflix.service;

import dev.com.movieflix.model.StreamingModel;
import dev.com.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository repository;

    public List<StreamingModel> listarTodos() {
        return repository.findAll();
    }

    public StreamingModel cadastrarStreaming(StreamingModel streaming) {
        return repository.save(streaming);
    }

    public Optional<StreamingModel> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletarStreaming(Long id) {
        repository.deleteById(id);
    }

}

