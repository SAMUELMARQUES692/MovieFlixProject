package dev.com.movieflix.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record CategoryRequest(@NotEmpty(message = "Nome da categoria é obrigatorio!") String name) {
}
