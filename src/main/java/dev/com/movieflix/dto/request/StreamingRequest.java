package dev.com.movieflix.dto.request;

import lombok.Builder;

@Builder
public record StreamingRequest(String name) {
}
