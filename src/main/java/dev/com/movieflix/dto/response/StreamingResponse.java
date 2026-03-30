package dev.com.movieflix.dto.response;

import lombok.Builder;

@Builder
public record StreamingResponse(Long id, String name) {
}
